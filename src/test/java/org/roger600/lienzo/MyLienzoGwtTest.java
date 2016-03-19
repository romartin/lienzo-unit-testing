package org.roger600.lienzo;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class MyLienzoGwtTest extends GWTTestCase {
    
    private MyLienzo myLienzo;

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
