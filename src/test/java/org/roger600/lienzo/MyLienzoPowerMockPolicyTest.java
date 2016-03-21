package org.roger600.lienzo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FlowPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.roger600.powermock.LienzoMockPolicy;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@MockPolicy(LienzoMockPolicy.class)
@PrepareForTest({MyLienzo.class})
public class MyLienzoPowerMockPolicyTest {
    
    private MyLienzo myLienzo;

    @Before
    public void setup() throws Exception {
        
    }

    @Test
    public void test() throws Exception {
        myLienzo = new MyLienzo();
        myLienzo.test();
        String color = myLienzo.getRectangle().getFillColor();
        System.out.println("Color is: " + color);
    }

}
