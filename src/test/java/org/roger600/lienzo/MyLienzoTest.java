package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.ContainerNode;
import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.tooling.nativetools.client.NArrayBaseJSO;
import com.ait.tooling.nativetools.client.NObjectBaseJSO;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GwtMockitoTestRunner.class) 
@WithClassesToStub({NObjectBaseJSO.class, 
        NObjectJSO.class, 
        NArrayBaseJSO.class, 
        NFastArrayList.class,
        NFastStringMap.class,
        Node.class,
        Shape.class,
        ContainerNode.class,
        RootPanel.class})
public class MyLienzoTest {
    
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
