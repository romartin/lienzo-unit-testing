package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.*;
import com.ait.lienzo.client.core.types.Transform;
import com.ait.tooling.nativetools.client.NArrayBaseJSO;
import com.ait.tooling.nativetools.client.NObjectBaseJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.GwtMockitoTestRunner3;
import com.google.gwtmockito.WithClassesToStub;
import com.google.gwtmockito.fakes.FakeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner3.class) 
@WithClassesToStub({NObjectBaseJSO.class, 
        //NObjectJSO.class, 
        NArrayBaseJSO.class, 
        NFastArrayList.class,
        NFastStringMap.class,
        Transform.TransformJSO.class,
        //Node.class,
        // Shape.class,
        Layer.class,
        ContainerNode.class,
        RootPanel.class})
public class MyLienzoStubsTest {
    
    private MyLienzo myLienzo;

    @Before
    public void setup() {
        myLienzo = new MyLienzo();
    }

    @Test
    public void test() {
        myLienzo.test();
        String color = myLienzo.getRectangle().getFillColor();
        System.out.println("Color is: " + color);
    }
    
}
