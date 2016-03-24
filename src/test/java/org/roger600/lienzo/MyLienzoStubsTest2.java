package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.LienzoMockitoTestRunner;


@RunWith(LienzoMockitoTestRunner.class)
public class MyLienzoStubsTest2 {
    
    private MyLienzo2 myLienzo;

    @Before
    public void setup() {
        myLienzo = new MyLienzo2();
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
