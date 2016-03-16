package org.roger600.lienzo;

import com.google.gwt.canvas.client.Canvas;

public class MyCanvas {

    private Canvas canvas;

    public MyCanvas() {
        canvas = Canvas.createIfSupported();
    }

    public void prova() {
        canvas.getContext2d().rect(0, 0, 50, 50);
    }
    
}
