package org.roger600.powermock;

import com.ait.lienzo.client.core.config.LienzoCore;
import com.ait.lienzo.client.core.i18n.MessageConstants;
import com.ait.lienzo.client.core.shape.IDrawable;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.FlowPanel;
import org.apache.commons.lang3.ArrayUtils;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMocks;
import org.powermock.core.spi.PowerMockPolicy;
import org.powermock.mockpolicies.MockPolicyClassLoadingSettings;
import org.powermock.mockpolicies.MockPolicyInterceptionSettings;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberMatcher.methodsDeclaredIn;
import static org.powermock.api.support.membermodification.MemberModifier.replace;
import static org.powermock.api.support.membermodification.MemberModifier.stub;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

public class LienzoMockPolicy implements PowerMockPolicy {
    
    @Override
    public void applyClassLoadingPolicy(MockPolicyClassLoadingSettings settings) {
        
        // Specify classes to be mocked/stubbed.
        settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(
                com.google.gwt.core.client.GWT.class.getName(),
                com.google.gwt.core.shared.GWT.class.getName(),
                Document.class.getName(),
                Element.class.getName(),
                Style.class.getName(),
                JavaScriptObject.class.getName());

        // Specify widgets to be mocked/stubbed.
       settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(getGwtWidgetsToMock());

        // Specify Lienzo assets and overlay types to be mocked/stubbed.
        try {
            settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(getLienzoOverlayTypesToMock());
            settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(getLienzoAssetsToMock());
        } catch (Exception e) {
            logError("Error applying class loader policy for lienzo assets / overlay types." , e);
        }

    }

    private String[] getLienzoAssetsToMock() {
        return new String[] {
            Layer.class.getName(),
            LienzoCore.class.getName(),
            Shape.class.getName(),
            Node.class.getName(),
            IDrawable.class.getName()
        };
    }

    private Class[] getLienzoOverlayTypeClassesToMock() throws Exception {
        return new Class[] {
                NObjectJSO.class, 
                Whitebox.getInnerClassType(NFastArrayList.class, "FastArrayListJSO"),
                Whitebox.getInnerClassType(NFastStringMap.class, "NFastStringMapJSO")
        };
    }
    
    private String[] getLienzoOverlayTypesToMock() throws Exception {
        Class[] overlayClasses = getLienzoOverlayTypeClassesToMock();
        String[] result = new String[overlayClasses.length];
        int x = 0;
        for ( Class c : overlayClasses ) {
            result[x] = c.getName();
            x++;
        }
        return result;
    }
    
    private String[] getGwtWidgetsToMock() {
        return new String[] {
          FlowPanel.class.getName()      
        };
    }

    @Override
    public void applyInterceptionPolicy(MockPolicyInterceptionSettings settings) {

        // GWT & bridge stubbing.
        final Method gwtGetVersionMethod = Whitebox.getMethod(GWT.class, "getVersion");
        settings.stubMethod(gwtGetVersionMethod, "2.7.0");
        replace(method(com.google.gwt.core.client.GWT.class,"create")).with(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> argument = (Class<?>) args[0];
                
                if ( argument.isAssignableFrom(MessageConstants.class) ) {
                    return getFake(MessageConstants.class);
                }
                
                return mock(argument);
            }
        });
        replace(method(com.google.gwt.core.shared.GWT.class,"create")).with(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> argument = (Class<?>) args[0];

                if ( argument.isAssignableFrom(MessageConstants.class) ) {
                    return getFake(MessageConstants.class);
                }
                
                return mock(argument);
            }
        });
        
        // Stub GWT DOM model.
        stubDomModel(settings);
        
        // Mock the different GWT widgets.
        mockGwtWidgets(settings);

        // Stub the different Lienzo overlay types.
        stubLienzoOverlayTypes(settings);

        // Stub the different Lienzo assets.
        stubLienzoAssets(settings);
    }
    
    private void stubDomModel(final MockPolicyInterceptionSettings settings) {
        // Document stubbing.
        /*final Method documentGetMethod = Whitebox.getMethod(Document.class, "get");
        replace(documentGetMethod).with(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return proxy;
            }
        });
        

        // Element stubbing.
        final Method elementGetTagNameMethod = Whitebox.getMethod(Element.class, "getTagName");
        settings.proxyMethod(elementGetTagNameMethod, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String className = proxy.getClass().getSimpleName();
                return className.substring(0, className.indexOf("Element")).toLowerCase();
            }
        });*/

        // Style style = mock(Style.class);
        // final Method elementGetStyleMethod = Whitebox.getMethod(Element.class, "getStyle");
        //settings.stubMethod(elementGetStyleMethod, style);

        suppress(method(JavaScriptObject.class, "cast"));

        stubAllMethods( settings, Document.class, new String[] {} );
        stubAllMethods( settings, Element.class, new String[] {} );
        stubAllMethods( settings, Style.class, new String[] {} );
    }
    
    private void stubAllMethods(MockPolicyInterceptionSettings settings, Class<?> clazz, String[] exceptions) {
        Method[] ms = methodsDeclaredIn(clazz);
        if ( null != ms) {
            for ( Method m : ms ) {
                
                String name = m.getName();
                if ( null != exceptions && ArrayUtils.contains(exceptions, name)) {
                    continue;
                }
                final Class<?> returnType = m.getReturnType();
                if ( null != returnType )  {

                    Object r = null;
                    if (returnType == String.class) {
                        r = "";
                    } else if (returnType == Boolean.class || "boolean".equals(returnType.getName()) ) {
                        r = false;
                    } else if (returnType == Byte.class || "byte".equals(returnType.getName()) ) {
                        r = (byte) 0;
                    } else if (returnType == Character.class || "char".equals(returnType.getName()) ) {
                        r = (char) 0;
                    } else if (returnType == Double.class || "double".equals(returnType.getName()) ) {
                        r = (double) 0;
                    } else if (returnType == Integer.class || "int".equals(returnType.getName()) ) {
                        r = (int) 0;
                    } else if (returnType == Float.class || "float".equals(returnType.getName()) ) {
                        r = (float) 0;
                    } else if (returnType == Long.class || "long".equals(returnType.getName())) {
                        r = (long) 0;
                    } else if (returnType == Short.class || "short".equals(returnType.getName()) ) {
                        r = (short) 0;
                    }
                    
                    if ( null != r ) {
                        
                        stub(m).toReturn(r);
                        
                    } else {
                        
                        replace(m).with(new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                return mock(returnType, new ReturnsCustomMocks());
                            }
                        });
                        
                    }


                } else {
                    suppress(m);
                }
            }
        }
    }
    
    private boolean isOverlayType(Class<?> clazz) {
        
        while ( clazz != null && clazz.getName() != null 
                && !"java.lang.Object".equals(clazz.getName()) 
                && !"com.google.gwt.core.client.JavaScriptObject".equals(clazz.getName()) ) {
            clazz = clazz.getSuperclass();
        }
        
        return clazz != null && clazz.getName() != null && "com.google.gwt.core.client.JavaScriptObject".equals(clazz.getName());
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T getFake(Class<?> type) {
        return (T) Proxy.newProxyInstance(LienzoMockPolicy.class.getClassLoader(), new Class<?>[] {type},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
                        if (method.getName().equals("ensureInjected")) {
                            return true;
                        } else if (method.getReturnType() == String.class) {
                            return buildMessage(method, args);
                        } else if (method.getReturnType() == SafeHtml.class) {
                            return SafeHtmlUtils.fromTrustedString(buildMessage(method, args));
                        } else {
                            throw new IllegalArgumentException(method.getName()
                                    + " must return either String or SafeHtml");
                        }
                    }
                });
    }

    private static String buildMessage(Method method, Object[] args) {
        StringBuilder message = new StringBuilder(method.getName());
        if (args == null || args.length == 0) {
            return message.toString();
        }

        message.append('(');
        message.append(stringify(args[0]));
        for (Object arg : Arrays.asList(args).subList(1, args.length)) {
            message.append(", ").append(stringify(arg));
        }
        return message.append(')').toString();
    }

    private static String stringify(Object arg) {
        if (arg == null) {
            return "null";
        } else if (arg instanceof SafeHtml) {
            return ((SafeHtml) arg).asString();
        } else if (arg instanceof SafeUri) {
            return ((SafeUri) arg).asString();
        } else {
            return arg.toString();
        }
    }

    private void stubLienzoAssets(MockPolicyInterceptionSettings settings) {
        suppress(method(Node.class, "cast"));
        suppress(constructor(LienzoCore.class));


    }

    private void stubLienzoOverlayTypes(MockPolicyInterceptionSettings settings) {
        try {
            
            // Stub the "make" method for all lienzo overlay types.
            Class[] overlayClasses = getLienzoOverlayTypeClassesToMock();
            for ( Class c : overlayClasses ) {
                Object type = mock(c);
                final Method jsoMakeMethod = Whitebox.getMethod(c, "make");
                settings.stubMethod(jsoMakeMethod, type);
            }
            
        } catch (Exception e) {
            logError("Error initializing mocks for Lienzo assets in Lienzo Mock Policy.", e);
        }
    }

    private void mockGwtWidgets(MockPolicyInterceptionSettings settings) {
        try {
            FlowPanel flowPanel = mock(FlowPanel.class);
            whenNew(FlowPanel.class).withNoArguments().thenReturn(flowPanel);
            whenNew(FlowPanel.class).withAnyArguments().thenReturn(flowPanel);
        } catch (Exception e) {
            logError("Error initializing mocks for GWT Widgets in Lienzo Mock Policy.", e);
        }
    }
    
    // TODO: Use right logger.
    private void logError(String message, Exception e) {
        System.out.println("[LienzoMockPolicy#ERROR] " + message);
        e.printStackTrace();
        throw new RuntimeException(message, e);
        
    }
    
}
