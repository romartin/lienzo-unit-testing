package com.google.gwt.core.client;

import com.google.gwt.core.client.impl.Impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class JavaScriptObject {
    
    // ROGER
    
    public String uuid;

    public String getUuid() {
        return uuid;
    }

    public static JavaScriptObject build(String uuid) {
        JavaScriptObject o = new JavaScriptObject();
        o.uuid = uuid;
        return o;
    }

    public static JavaScriptObject build(String className, String uuid) {
        try {
            Class clazz = Class.forName(className);
            Constructor c = clazz.getDeclaredConstructors()[0];
            c.setAccessible(true);
            return (JavaScriptObject) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Returns a new array.
     */
    public static native JavaScriptObject createArray() /*-{
        var theObject=[];
    }-*/;

    /**
     * Returns a new array with a given size.
     *
     * <p>Consider using this method in performance critical code instead of using
     * {@link #createArray()}, since this gives more hints to the underlying
     * JavaScript VM for optimizations.
     */
    
    // Javassist Modifiers : 265
    public static native JavaScriptObject createArray(int size) /*-{
        var theObject = new Array(size);
    }-*/;

    /**
     * Returns an empty function.
     */

    // Javassist Modifiers : 265
    public static native JavaScriptObject createFunction() /*-{
        var theObject= function() {
        };
    }-*/;

    /**
     * Returns a new object.
     */

    // Javassist Modifiers : 265
    public static native JavaScriptObject createObject() /*-{
        var theObject = {};
    }-*/;

    /**
     * Helper for {@link #toString()}, for lighter "more production" code.
     */

    // Javassist Modifiers : 266
    private static native String toStringSimple(JavaScriptObject obj) /*-{
        return obj.toString ? obj.toString() : '[JavaScriptObject]';
    }-*/;

    /**
     * Helper for {@link #toString()}, when Development Mode or assertions are on.
     */
    private static native String toStringVerbose(JavaScriptObject obj) /*-{
        return '[JavaScriptObject]';
    }-*/;

    /**
     * Not directly instantiable. All subclasses must also define a protected,
     * empty, no-arg constructor.
     */
    protected JavaScriptObject() {
    }

    /**
     * A helper method to enable cross-casting from any {@link JavaScriptObject}
     * type to any other {@link JavaScriptObject} type.
     *
     * @param <T> the target type
     * @return this object as a different type
     */
    @SuppressWarnings("unchecked")
    public final <T extends JavaScriptObject> T cast() {
        System.out.println("CASTTTTTTTTTTTTTTTTTT");
        return (T) this;
    }

    /**
     * Returns <code>true</code> if the objects are JavaScript identical
     * (triple-equals).
     */
    @Override
    public final boolean equals(Object other) {
        return super.equals(other);
    }

    /**
     * Uses a monotonically increasing counter to assign a hash code to the
     * underlying JavaScript object. Do not call this method on non-modifiable
     * JavaScript objects.
     *
     * TODO: if the underlying object defines a 'hashCode' method maybe use that?
     *
     * @return the hash code of the object
     */
    @Override
    public final int hashCode() {
        return Impl.getHashCode(this);
    }

    /**
     * Call the toSource() on the JSO.
     */
    public native String toSource() /*-{
        this.toSource ? this.toSource() : 'NO SOURCE';
    }-*/;

    /**
     * Makes a best-effort attempt to get a useful debugging string describing the
     * given JavaScriptObject. In Production Mode with assertions disabled, this
     * will either call and return the JSO's toString() if one exists, or just
     * return "[JavaScriptObject]". In Development Mode, or with assertions
     * enabled, some stronger effort is made to represent other types of JSOs,
     * including inspecting for document nodes' outerHTML and innerHTML, etc.
     */
    @Override
    public final String toString() {
        return JavaScriptObject.class.desiredAssertionStatus() ?
                toStringVerbose(this) : toStringSimple(this);
    }

}
