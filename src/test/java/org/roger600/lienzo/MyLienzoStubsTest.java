package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.LienzoMockitoTestRunner;
import org.roger600.lienzo.mockito.annotation.Settings;
import org.roger600.lienzo.mockito.util.EventUtils;


@RunWith( LienzoMockitoTestRunner.class )
@Settings( logEnabled = true )
public class MyLienzoStubsTest {
    
    private MyLienzo myLienzo;

    static {
        // LienzoMockitoLogger.enable(System.out);
    }
    
    @Before
    public void setup() {
        myLienzo = new MyLienzo();
    }

    @Test
    public void test() {
        myLienzo.test();
        int size = myLienzo.getLayer().getChildNodes().size();
        String color1 = ( (Rectangle) myLienzo.getLayer().getChildNodes().get(0)).getFillColor();
        System.out.println("Fill color is: " + color1);
        System.out.println("Size is: " + size);
        Assert.assertEquals("#0000FF", color1);
        Assert.assertEquals(1, size);
    }

    @Test
    public void test2() throws Exception {

        Rectangle rectangle = (Rectangle) myLienzo.getLayer().getChildNodes().get(0);

        EventUtils.click(rectangle, 50, 50);
        prova(rectangle);
    }
    
    private void prova(Node node) {
        System.out.println(node);
    }
    
}
