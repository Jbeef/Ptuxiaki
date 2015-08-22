package com.lisp.services.ejb;

import com.lisp.interceptors.SelectPackage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import javax.script.ScriptEngine;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Symbol;
import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

/**
 *
 * @author Akis
 */
@Singleton
@SelectPackage
public class SingletonInterpreterBean implements SingletonInterpreterBeanLocal {

    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;

    private Interpreter interpreter;

    @PostConstruct
    @ExcludeClassInterceptors
    private void init() {
        interpreter = singletonStartupBean.getInstance();
    }

    @Override
    @Lock(LockType.READ)
    public String executeCommand(Package homePackage, String command) {
        String result;
        ScriptEngine engine = new AbclScriptEngineFactory().getScriptEngine();

        try {
            engine.eval(command);
            LispObject object = interpreter.eval(command);

            result = object.princToString();
        } catch (Throwable t) {
            System.out.println("#######    INTERPRETER EXCEPTION     ######");
            result = t.getMessage();
            System.out.println(result);
        }
        return result;
    }

    @Override
    @Lock(LockType.WRITE)
    @AccessTimeout(unit = TimeUnit.SECONDS, value = 6)
    public String executeFromFile(Package homePackage) {
        interpreter.eval("(load \"C:/Users/Akis/Desktop/file.lisp\")");
        //"(load \"Users/Liferay/Desktop/file.lisp\")"  
        Package defaultPackage = Packages.findPackage(homePackage.getName());
        Symbol sym = defaultPackage.findAccessibleSymbol("LISPFUNCTION");
        sym.getSymbolFunction().execute();

        Symbol[] symbols = defaultPackage.symbols();
        System.out.println(symbols.length);
        for (Symbol s : symbols) {
            System.out.println(s.getName() + "  " + s.getQualifiedName());
        }

        return null;
    }
// (unintern 'function)

    @Override
    @Lock(LockType.READ)
    public List<Symbol> getSymbolsFromFile(Package homePackage, String url) {
        List<Symbol> listOfSymbols = new ArrayList<>();

        interpreter.eval("(load " + "\"" + url + "\"" + ")");
        //Package defaultPackage = singletonStartupBean.findPackage(homePackage.getName());

        if (homePackage != null) {
            listOfSymbols = Arrays.asList(homePackage.symbols());
        }
        return listOfSymbols;
    }

}
