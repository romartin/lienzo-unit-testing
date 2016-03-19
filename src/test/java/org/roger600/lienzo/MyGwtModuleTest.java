package org.roger600.lienzo;


import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class MyGwtModuleTest extends GWTTestCase {

    private MyGwtModule module;
    
    @Test
    public void test() {
        module = new MyGwtModule();
        assertNotNull(module.getMainPanel());
    }

    @Override
    public String getModuleName() {
        return "org.roger600.LienzoUnitTesting";
    }
}
