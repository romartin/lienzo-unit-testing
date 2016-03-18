package com.ait.tooling.nativetools.client;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.HashMap;
import java.util.Map;

public class NObjectJSO extends JavaScriptObject {

    private Map<String, Object> attributes = new HashMap<String, Object>();

    public static final NObjectJSO prova() {
        NObjectJSO ret = new NObjectJSO();
        ret.uuid = "";
        return ret;
    }
    
    public static final NObjectJSO make()
    {
        return new NObjectJSO();
    }

    protected NObjectJSO()
    {
    }

    public static final NObjectJSO cast(final Object jso)
    {
        if (null != jso)
        {
            return (NObjectJSO) jso;
        }
        return null;
    }

    public final void put(final String name, final int value)
    {
        attributes.put(name, value);
    }

    public final void put(final String name, final double value)
    {
        attributes.put(name, value);
    }

    public final void put(final String name, final boolean value)
    {
        attributes.put(name, value);
    }

    public final void put(final String name, final String value)
    {
        attributes.put(name, value);
    }

    public final void put(final String name, final NHasJSO<? extends JavaScriptObject> value)
    {
        if (null != value)
        {
            attributes.put(name, value);
        }
        else
        {
            attributes.put(name, value);
        }
    }

    public final void put(final String name, final JavaScriptObject value)
    {
        attributes.put(name, value);
    }

    public final NValue<?> getAsNValue(final String name)
    {
        return (NValue<?>) attributes.get(name);
    }

    public final JavaScriptObject getAsJSO(final String name)
    {
        return (JavaScriptObject) attributes.get(name);
    }

    public final int getAsInteger(final String name)
    {
        return (Integer) attributes.get(name);
    }

    public final double getAsDouble(final String name)
    {
        return (Double) attributes.get(name);
    }

    public final boolean getAsBoolean(final String name)
    {
        return (Boolean) attributes.get(name);
    }

    public final String getAsString(final String name)
    {
        return (String) attributes.get(name);
    }

    public final String getAsString(final String name, final String otherwise)
    {
        final String value =  (String) attributes.get(name);

        return ((null != value) ? value : otherwise);
    }

    public final boolean isArray(final String name)
    {
        return attributes.get(name) instanceof JavaScriptObject;
    }

    public final boolean isEmpty()
    {
        return attributes.isEmpty();
    }

    public final boolean isString(final String name)
    {
        return attributes.get(name) != null && attributes.get(name) instanceof String;
    }

    public final boolean isNumber(final String name)
    {
        return attributes.get(name) != null && attributes.get(name) instanceof Number;
    }

    public final boolean isInteger(final String name)
    {
        return attributes.get(name) != null && attributes.get(name) instanceof Integer;
    }

    public final boolean isBoolean(final String name)
    {
        return attributes.get(name) != null && attributes.get(name) instanceof Boolean;
    }

    public final boolean isObject(final String name)
    {
        return attributes.get(name) != null && attributes.get(name) instanceof JavaScriptObject;
    }

    public final boolean isDefined(final String name)
    {
        return attributes.containsKey(name);
    }

    public final void remove(final String name)
    {
        attributes.remove(name);
    }

}
