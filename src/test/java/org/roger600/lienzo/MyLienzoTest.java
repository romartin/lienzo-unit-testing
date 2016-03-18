package org.roger600.lienzo;

import com.ait.lienzo.client.core.shape.*;
import com.ait.lienzo.client.core.types.Transform;
import com.ait.tooling.nativetools.client.NArrayBaseJSO;
import com.ait.tooling.nativetools.client.NObjectBaseJSO;
import com.ait.tooling.nativetools.client.NObjectJSO;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import com.ait.tooling.nativetools.client.collection.NFastStringMap;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import com.google.gwtmockito.fakes.FakeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class) 
@WithClassesToStub({NObjectBaseJSO.class, 
        //NObjectJSO.class, 
        NArrayBaseJSO.class, 
        NFastArrayList.class,
        NFastStringMap.class,
        Transform.TransformJSO.class,
        // Node.class,
        //Shape.class,
        Layer.class,
        ContainerNode.class,
        RootPanel.class})
public class MyLienzoTest {
    
    private MyLienzo myLienzo;

    @Mock
    Shape shape;
    @Mock
    Node node;
    @Mock Attributes attributes;
    
    @Mock Transform transform;
    
    @Before
    public void setup() {

        when(attributes.getTransform()).thenReturn(transform);
        when(attributes.getFillColor()).thenReturn("color");

        when(shape.getAttributes()).thenReturn(attributes);
        when(node.getAttributes()).thenReturn(attributes);
        
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.println("Setting fill color!");
                return null;
            }
        }).when(attributes).setFillColor(anyString());
        
        GwtMockito.useProviderForType(Shape.class, new FakeProvider<Shape>() {
            @Override
            public Shape getFake(Class<?> aClass) {
                return shape;
            }
        });

        GwtMockito.useProviderForType(Node.class, new FakeProvider<Node>() {
            @Override
            public Node getFake(Class<?> aClass) {
                return node;
            }
        });
        
        myLienzo = new MyLienzo();
    }

    @Test
    public void test() {
        myLienzo.test();
        String color = myLienzo.getRectangle().getFillColor();
        System.out.println("Color is: " + color);
    }
    
}