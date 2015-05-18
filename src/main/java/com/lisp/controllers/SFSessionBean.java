package com.lisp.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.LispThread;

/**
 *
 * @author Liferay
 */
@Stateful
public class SFSessionBean {
    
    private Interpreter interpreter;
    
    @PostConstruct
    public void init() {
        interpreter = Interpreter.getInstance();
        if (interpreter == null) {
            interpreter = Interpreter.createInstance();
        }

        System.out.println("Interpreter init from State: " + interpreter.hashCode());
    }

    public void service(String command) {
        LispObject result = Interpreter.evaluate(command);
        
        
        
        System.out.println(result.princToString());

        //interpreter.dispose();

    }

}
