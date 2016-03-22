package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.*;
import com.ait.lienzo.client.core.types.Transform;
import com.ait.tooling.nativetools.client.NArrayBaseJSO;
import com.ait.tooling.nativetools.client.NObjectBaseJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GwtMockitoTestRunner.class)
public class MyLienzoStubsTest {
    
    private MyLienzo myLienzo;

    @Before
    public void setup() {
        myLienzo = new MyLienzo();
    }

    @Test
    public void test() {
        myLienzo.test();
        int size = myLienzo.getLayer().getChildNodes().size();
        String color1 = ( (Rectangle) myLienzo.getLayer().getChildNodes().get(0)).getFillColor();
        String color2 = ( (Rectangle) myLienzo.getLayer().getChildNodes().get(1)).getFillColor();
        System.out.println("Color1 is: " + color1);
        System.out.println("Color2 is: " + color2);
        System.out.println("Size is: " + size);
        Assert.assertEquals("#0000FF", color1);
        Assert.assertEquals("#FF00FF", color2);
        Assert.assertEquals(2, size);
    }
    
}
