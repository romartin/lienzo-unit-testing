package org.roger600.lienzo;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Before;
import org.junit.Test;

public class MyLienzoTest extends GWTTestCase {
    
    private MyLienzo myLienzo;

    @Before
    public void setup() {
        
    }

    @Test
    public void test() {
        myLienzo = new MyLienzo();
        myLienzo.test();
        String color = myLienzo.getRectangle().getFillColor();
        System.out.println("Color is: " + color);
    }

    @Override
    public String getModuleName() {
        return "org.roger600.LienzoUnitTesting";
    }
}
