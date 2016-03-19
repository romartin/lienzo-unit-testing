package org.roger600.lienzo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class MyGwtModule implements EntryPoint {

    private FlowPanel mainPanel = new FlowPanel();
    
    @Override
    public void onModuleLoad() {
        RootPanel.get().add(mainPanel);
    }

    public FlowPanel getMainPanel() {
        return mainPanel;
    }
}
