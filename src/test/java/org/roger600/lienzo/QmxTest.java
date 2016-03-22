package org.roger600.lienzo;

import com.ait.lienzo.client.core.types.Point2D;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GwtMockitoTestRunner.class)
public class QmxTest {
    

    @Before
    public void setup() {
    }

    @Test
    public void test() {
        Point2D p = new Point2D(0,0).add(new Point2D(10,10));
        System.out.println(p);
    }

}
