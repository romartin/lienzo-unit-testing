package org.roger600.lienzo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GWT.class,Document.class})
public class MyLienzoPowerMockTest {
    
    @Mock Document document;
    
    private MyLienzo myLienzo;

    @Before
    public void setup() throws Exception {
        PowerMockito.mockStatic(GWT.class);
        PowerMockito.mockStatic(Document.class);
        Mockito.when(GWT.getVersion()).thenReturn("2.7.0");
        Mockito.when(Document.get()).thenReturn(document);
        PowerMockito.when(document, "nativeGet").thenReturn(document);
    }

    @Test
    public void test() {
        myLienzo = new MyLienzo();
        myLienzo.test();
        String color = myLienzo.getRectangle().getFillColor();
        System.out.println("Color is: " + color);
    }

}
