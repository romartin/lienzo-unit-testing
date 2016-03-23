package org.roger600.lienzo;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class MyLienzoSpied {

    private FlowPanel mainPanel = new FlowPanel();
    private LienzoPanel panel = new LienzoPanel(600, 600);
    private final Layer layer;
    private Rectangle rectangle = new Rectangle(50, 50);
    
    public MyLienzoSpied(Layer layer) {
        this.layer = layer;
        mainPanel.add(panel);
        panel.add(layer);
        layer.add(rectangle);
        rectangle.addNodeMouseClickHandler(new NodeMouseClickHandler() {
            @Override
            public void onNodeMouseClick(NodeMouseClickEvent event) {
                System.out.println("Mouse CLICK event fired on rectangle!");
            }
        });
        layer.draw();
    }
    
    public void test() {
        rectangle.setFillColor("#0000FF");
    }
    
    public void test2() {
        layer.add(new Circle(50));
        rectangle.setFillColor("#000022");
    }

    public LienzoPanel getPanel() {
        return panel;
    }

    public Layer getLayer() {
        return layer;
    }

}
