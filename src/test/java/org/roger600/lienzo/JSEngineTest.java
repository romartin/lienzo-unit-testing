package org.roger600.lienzo;

import com.google.gwtmockito.GwtMockitoTestRunner4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roger600.lienzo.js.MyJSObjectHolder;

@RunWith(GwtMockitoTestRunner4.class)
public class JSEngineTest {


    MyJSObjectHolder holder;
    
    @Before
    public void setup() {
        holder = new MyJSObjectHolder();    
    }

    @Test
    public void test() {
        holder.setName();
        String n = holder.getName();
        System.out.println("Name is: " + n);
    }
    
}