package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.util.StubUtils;


@RunWith(GwtMockitoTestRunner.class)
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

    @Test
    public void test2() throws Exception {
        test();

        Rectangle rectangle = (Rectangle) myLienzo.getLayer().getChildNodes().get(0);
        StubUtils.invoke(rectangle, "doAMethodAddTest");

        
        rectangle.fireEvent(new ClickEvent(){
            
            @Override
            public int getClientX() {
                return 50;
            }

            @Override
            public int getClientY() {
                return 50;
            }

            @Override
            public boolean isAltKeyDown() {
                return false;
            }

            @Override
            public boolean isControlKeyDown() {
                return false;
            }

            @Override
            public boolean isMetaKeyDown() {
                return false;
            }

            @Override
            public boolean isShiftKeyDown() {
                return false;
            }
        });

        //myLienzo.getPanel().fireEvent(new ClickEvent(){});

    }
    
}
