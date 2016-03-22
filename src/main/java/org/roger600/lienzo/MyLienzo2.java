package org.roger600.lienzo;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class MyLienzo2 {

    private FlowPanel mainPanel = new FlowPanel();
    private LienzoPanel panel = new LienzoPanel(600, 600);
    private Layer layer = new Layer();
    private Rectangle rectangle = new Rectangle(50, 50);
    private Rectangle rectangle2 = new Rectangle(50, 50);
    
    public MyLienzo2() {
        mainPanel.add(panel);
        panel.add(layer);
        layer.add(rectangle);
        rectangle.addNodeMouseClickHandler(new NodeMouseClickHandler() {
            @Override
            public void onNodeMouseClick(NodeMouseClickEvent event) {
                System.out.println("Mouse CLICK event fired on rectangle!");
            }
        });
        rectangle2.addNodeMouseClickHandler(new NodeMouseClickHandler() {
            @Override
            public void onNodeMouseClick(NodeMouseClickEvent event) {
                System.out.println("Mouse CLICK event fired on rectangle2!");
            }
        });
        layer.add(rectangle2);
        layer.draw();
    }
    
    public void test() {
        rectangle.setFillColor("#0000FF");
        rectangle2.setFillColor("#FF00FF");
    }

    public LienzoPanel getPanel() {
        return panel;
    }

    public Layer getLayer() {
        return layer;
    }

}
