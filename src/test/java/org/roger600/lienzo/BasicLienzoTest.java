package org.roger600.lienzo;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.mockito.LienzoMockitoTestRunner;

@RunWith(LienzoMockitoTestRunner.class)
public class BasicLienzoTest {

    public class MyLienzo {

        private FlowPanel mainPanel = new FlowPanel();
        private LienzoPanel panel = new LienzoPanel(600, 600);
        private Layer layer = new Layer();
        private Rectangle rectangle = new Rectangle(50, 50);

        public MyLienzo() {
            mainPanel.add(panel);
            panel.add(layer);
            layer.add(rectangle);
            rectangle.addNodeMouseClickHandler(new NodeMouseClickHandler() {
                public void onNodeMouseClick(NodeMouseClickEvent event) {
                    System.out.println("Mouse CLICK event fired on rectangle!");
                }
            });
            layer.draw();
        }

        public void test() {
            rectangle.setFillColor("#0000FF");
        }

        public LienzoPanel getPanel() {
            return panel;
        }

        public Layer getLayer() {
            return layer;
        }

    }

    private MyLienzo myLienzo;

    @Before
    public void setup() {
        myLienzo = new MyLienzo();
    }

    @Test
    public void test() {
        myLienzo.test();
        int size = myLienzo.getLayer().getChildNodes().size();
        String color1 = ( (Rectangle) myLienzo.getLayer().getChildNodes().get(0)).getFillColor();
        System.out.println("Color1 is: " + color1);
        System.out.println("Size is: " + size);
        Assert.assertEquals("#0000FF", color1);
        Assert.assertEquals(1, size);
    }
    
}
