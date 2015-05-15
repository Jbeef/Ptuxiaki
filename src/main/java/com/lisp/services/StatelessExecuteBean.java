package com.lisp.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import org.armedbear.lisp.Function;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.LispThread;
import org.armedbear.lisp.Load;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Symbol;
import org.armedbear.lisp.scripting.AbclScriptEngine;
import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

/**
 *
 * @author Liferay
 */
@Stateless
public class StatelessExecuteBean implements StatelessExecuteBeanLocal {

    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;
    @Resource
    SessionContext context;

    private Interpreter interpreter;

    @PostConstruct
    public void init() {
        interpreter = singletonInterpreterBean.createInstance();
    }

    @Override
    public String executeCommand(String command) {

        System.out.println("SSB: " + context.hashCode());
        System.out.println("Interpreter: " + interpreter.hashCode());
        //interpreter.eval("(makunbound 'x)");
        LispObject result = interpreter.eval(command);

        //Package defaultPackage = Packages.findPackage("CL-USER");                
        //Symbol sym = defaultPackage.findAccessibleSymbol("LISPFUNCTION");
        //Function function = (Function) sym.getSymbolFunction();
        //LispObject lisp = function.execute();        
        //return lisp.getStringValue();
        AbclScriptEngine engine = (AbclScriptEngine) new AbclScriptEngineFactory().getScriptEngine();

        try {

            SimpleScriptContext ctx = new SimpleScriptContext();
            Bindings b = engine.createBindings();
            ctx.setBindings(b, ScriptContext.ENGINE_SCOPE);
            
            System.out.println("# Result: " + engine.eval(command, ctx));
        } catch (ScriptException ex) {
            Logger.getLogger(StatelessExecuteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";//String.valueOf(result.cdr().intValue());
    }

}
