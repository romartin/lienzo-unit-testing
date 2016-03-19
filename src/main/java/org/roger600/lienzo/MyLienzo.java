package org.roger600.lienzo;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.FlowPanel;

public class MyLienzo {

    private FlowPanel mainPanel = new FlowPanel();
    private LienzoPanel panel = new LienzoPanel(600, 600);
    private Layer layer = new Layer();
    private Rectangle rectangle = new Rectangle(50, 50);
    
    public MyLienzo() {
        mainPanel.add(panel);
        panel.add(layer);
        layer.add(rectangle);
        rectangle.setFillColor("#FF0000");
        rectangle.addNodeMouseClickHandler(new NodeMouseClickHandler() {
            @Override
            public void onNodeMouseClick(NodeMouseClickEvent event) {
                rectangle.setFillColor("#0000FF");
            }
        });
        layer.draw();
    }
    
    public void test() {

       /* NativeEvent event = Document.get().createClickEvent(1, 
                (int) rectangle.getAbsoluteLocation().getX(), 
                (int) rectangle.getAbsoluteLocation().getY(),
                (int) rectangle.getX(),
                (int) rectangle.getY(),
                false,
                false,
                false,
                false);
        
        DomEvent.fireNativeEvent(event, panel);*/

        /*rectangle.fireEvent(new GwtEvent<ClickHandler>() {
            @Override
            public DomEvent.Type<ClickHandler> getAssociatedType() {
                return ClickEvent.getType();
            }

            @Override
            protected void dispatch(ClickHandler handler) {
                handler.onClick(null);
            }
        });*/

        rectangle.setFillColor("#00FF00");
        
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
