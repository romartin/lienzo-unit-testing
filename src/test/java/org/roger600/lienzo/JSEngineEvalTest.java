package org.roger600.lienzo;

import javax.script.*;

public class JSEngineEvalTest {

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = getEngine();
       
        engine.put("ooo","eval('return {};')");
        engine.eval("     print(ooo) ");
        /*engine.put("name", "nom");
        engine.put("value", "Roger");
        engine.eval("var prova={}; prova[name] = value;");
        engine.eval("print(prova[name]);");*/

        System.out.println("");
       
        
    }
    
    public static void newContext() {
        ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
    }


    public static ScriptEngine getEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        return manager.getEngineByName("rhino");
    }
    
}
