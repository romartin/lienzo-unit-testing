package org.roger600.lienzo;

import com.ait.lienzo.client.core.types.Point2D;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.LienzoMockitoTestRunner;


@RunWith(LienzoMockitoTestRunner.class)
public class PointsTest {
    

    @Before
    public void setup() {
    }

    @Test
    public void test() {
        Point2D p = new Point2D(0,0).add(new Point2D(10,10));
        System.out.println(p);
    }

}
