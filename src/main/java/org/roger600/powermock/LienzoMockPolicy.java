package org.roger600.powermock;

import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FlowPanel;
import org.apache.tools.ant.taskdefs.Java;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.spi.PowerMockPolicy;
import org.powermock.mockpolicies.MockPolicyClassLoadingSettings;
import org.powermock.mockpolicies.MockPolicyInterceptionSettings;
import org.powermock.reflect.Whitebox;

import javax.print.Doc;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.everythingDeclaredIn;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.replace;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

public class LienzoMockPolicy implements PowerMockPolicy {
    
    @Override
    public void applyClassLoadingPolicy(MockPolicyClassLoadingSettings settings) {
        
        // Specify classes to be mocked/stubbed.
        settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(
                com.google.gwt.core.client.GWT.class.getName(),
                com.google.gwt.core.shared.GWT.class.getName(),
                Document.class.getName(),
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
            Shape.class.getName(),
            Node.class.getName()
        };
    }

    private Class[] getLienzoOverlayTypeClassesToMock() throws Exception {
        return new Class[] {
                NObjectJSO.class, 
                Whitebox.getInnerClassType(NFastArrayList.class, "FastArrayListJSO")
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
                return mock(argument);
            }
        });
        replace(method(com.google.gwt.core.shared.GWT.class,"create")).with(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> argument = (Class<?>) args[0];
                return mock(argument);
            }
        });
        

        // Document stubbing.
        Document document = mock(Document.class);
        final Method documentGetMethod = Whitebox.getMethod(Document.class, "get");
        settings.stubMethod(documentGetMethod, document);

        // Mock the different GWT widgets.
        mockGwtWidgets(settings);

        // Stub the different Lienzo overlay types.
        stubLienzoOverlayTypes(settings);

        // Stub the different Lienzo assets.
        stubLienzoAssets(settings);
    }

    private void stubLienzoAssets(MockPolicyInterceptionSettings settings) {
        suppress(method(JavaScriptObject.class, "cast"));
        suppress(method(Node.class, "cast"));
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
