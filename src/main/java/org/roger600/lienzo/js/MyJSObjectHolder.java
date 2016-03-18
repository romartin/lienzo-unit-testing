package org.roger600.lienzo.js;

public class MyJSObjectHolder {
    
    private MyJSObject myJSObject;

    public MyJSObjectHolder() {
        myJSObject = MyJSObject.make();
    }
    
    public void setName() {
        myJSObject.put("name","Roger1");
    }

    public String getName() {
        return myJSObject.get("name");
    }
    
}
