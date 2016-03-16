package org.roger600.lienzo;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.fakes.FakeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class MyCanvasTest {

    @Mock
    Canvas canvas;

    @GwtMock
    Context2d context2d;

    private MyCanvas myCanvas;

    @Before
    public void setup() {
        when(canvas.getContext2d()).thenReturn(context2d);

        GwtMockito.useProviderForType(Canvas.class, new FakeProvider<Canvas>() {
            @Override
            public Canvas getFake(Class<?> aClass) {
                return canvas;
            }
        });

        myCanvas = new MyCanvas();
    }

    @Test
    public void test() {
        myCanvas.prova();
    }
    
}
