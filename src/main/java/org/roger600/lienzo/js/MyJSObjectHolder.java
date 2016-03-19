package org.roger600.lienzo.js;

public class MyJSObjectHolder {
    
    private MyJSObject myJSObject;
    private MyJSObject myJSObject2;

    public MyJSObjectHolder() {
        myJSObject = MyJSObject.make();
        myJSObject2 = MyJSObject.make();
    }
    
    public void setName() {
        myJSObject.put("name","Roger1");
        myJSObject2.put("name","Roger12");
    }

    public void setAnotherName() {
        myJSObject.put("name","Roger3");
        myJSObject2.put("name","Roger34");
    }

    public String getName() {
        return myJSObject.get("name") + " " + myJSObject2.get("name");
    }
    
}
