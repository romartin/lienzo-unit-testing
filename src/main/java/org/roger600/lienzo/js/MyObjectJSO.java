package org.roger600.lienzo.js;

import com.google.gwt.core.client.JavaScriptObject;

public class MyObjectJSO extends com.google.gwt.core.client.JavaScriptObject {

    public static final MyObjectJSO make() {
        return JavaScriptObject.createObject().cast();
    }
    
    protected MyObjectJSO() {
    }

    public final native void put(String name, String value)
    /*-{
        this[name] = value;
    }-*/;

    public final native String get(String name)
    /*-{
        return this[name];
    }-*/;
    
}
