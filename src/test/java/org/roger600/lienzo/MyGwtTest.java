package org.roger600.lienzo;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GwtMockitoTestRunner.class)
public class MyGwtTest {
    
    private FlowPanel panel;

    @Before
    public void setup() {
        panel = new FlowPanel();
    }

    @Test
    public void test() {
        panel.clear();
    }
    
}
