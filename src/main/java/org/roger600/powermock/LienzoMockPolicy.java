package org.roger600.powermock;

import com.ait.tooling.nativetools.client.NObjectJSO;
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
    
    Document document;
    FlowPanel flowPanel;
    NObjectJSO nObjectJSO;

    @Override
    public void applyClassLoadingPolicy(MockPolicyClassLoadingSettings settings) {
        
        // Specify classes to be mocked.
        settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(
                com.google.gwt.core.client.GWT.class.getName(),
                com.google.gwt.core.shared.GWT.class.getName(),
                Document.class.getName(),
                JavaScriptObject.class.getName());

        // Specify widgets to be mocked.
       settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(getGwtWidgetsToMock());

        // Specify Lienzo assets to be mocked.
        settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(getLienzoAssetsToMock());
    }

    private String[] getLienzoAssetsToMock() {
        return new String[] {
            NObjectJSO.class.getName()
        };
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
        final Method documentGetMethod = Whitebox.getMethod(Document.class, "get");
        settings.stubMethod(documentGetMethod, document);

        // Ensure mocked objects are initialized.
        initMocks(settings);

        // Mock the different GWT widgets.
        mockGwtWidgets(settings);

        // Mock the different Lienzo assets.
        mockLienzoAssets(settings);
    }
    
    private void mockGwtWidgets(MockPolicyInterceptionSettings settings) {
        try {
            whenNew(FlowPanel.class).withNoArguments().thenReturn(flowPanel);
            whenNew(FlowPanel.class).withAnyArguments().thenReturn(flowPanel);
        } catch (Exception e) {
            logError("Error initializing mocks for GWT Widgets in Lienzo Mock Policy.", e);
        }
    }

    private void mockLienzoAssets(MockPolicyInterceptionSettings settings) {
        try {
            // NObjectJSO.
            final Method nOjectJSOMakeMethod = Whitebox.getMethod(NObjectJSO.class, "make");
            settings.stubMethod(nOjectJSOMakeMethod, nObjectJSO);
            
            
        } catch (Exception e) {
            logError("Error initializing mocks for Lienzo assets in Lienzo Mock Policy.", e);
        }
    }
    
    private void initMocks(MockPolicyInterceptionSettings settings) {
        try {
            this.nObjectJSO = mock(NObjectJSO.class);
            this.document = mock(Document.class);
            this.flowPanel = mock(FlowPanel.class);
        } catch (Exception e) {
            logError("Error initializing mocks in Lienzo Mock Policy.", e);
        }
    }
    
    // TODO: Use right logger.
    private void logError(String message, Exception e) {
        System.out.println("[LienzoMockPolicy#ERROR] " + message);
        e.printStackTrace();
        throw new RuntimeException(message, e);
        
    }
    
}
