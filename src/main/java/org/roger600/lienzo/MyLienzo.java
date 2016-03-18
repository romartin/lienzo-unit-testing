package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class MyLienzo {

    private FlowPanel mainPanel = new FlowPanel();
    private LienzoPanel panel = new LienzoPanel(600, 600);
    private Layer layer = new Layer();
    private Rectangle rectangle = new Rectangle(50, 50);
    
    public MyLienzo() {
        RootPanel.get().add(mainPanel);
        mainPanel.add(panel);
        panel.add(layer);
        layer.add(rectangle);
        layer.draw();
    }
    
    public void test() {
        rectangle.setFillColor("#0000FF");
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
