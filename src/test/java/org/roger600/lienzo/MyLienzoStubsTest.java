package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.LienzoMockitoTestRunner;
import org.roger600.lienzo.mockito.util.EventUtils;
import org.roger600.lienzo.mockito.util.StubUtils;


@RunWith(LienzoMockitoTestRunner.class)
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
        System.out.println("Color1 is: " + color1);
        System.out.println("Size is: " + size);
        Assert.assertEquals("#0000FF", color1);
        Assert.assertEquals(1, size);
    }

    @Test
    public void test2() throws Exception {
        test();

        Rectangle rectangle = (Rectangle) myLienzo.getLayer().getChildNodes().get(0);
        StubUtils.invoke(rectangle, "doAMethodAddTest");
    }


    @Test
    public void test3() throws Exception {

        Rectangle rectangle = (Rectangle) myLienzo.getLayer().getChildNodes().get(0);

        // EventUtils.click(rectangle, 50, 50);
    }
    
}
