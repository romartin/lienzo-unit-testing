package org.roger600.lienzo.js;

import com.google.gwt.core.client.JavaScriptObject;

public class MyJSObject extends com.google.gwt.core.client.JavaScriptObject {

    public static final MyJSObject make() {
        return JavaScriptObject.createObject().cast();
    }
    
    public MyJSObject() {
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
