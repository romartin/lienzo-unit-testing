package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.lienzo.util.StubUtils;
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

    @Test
    public void test2() throws Exception {
        test();

        Rectangle rectangle = (Rectangle) myLienzo.getLayer().getChildNodes().get(0);
        StubUtils.invoke(rectangle, "doAMethodAddTest");

    }
    
}
