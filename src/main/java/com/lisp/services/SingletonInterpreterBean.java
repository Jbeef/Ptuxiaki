package com.lisp.services;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.armedbear.lisp.Interpreter;

/**
 *
 * @author Liferay
 */
@Singleton
@Startup
public class SingletonInterpreterBean implements SingletonInterpreterBeanLocal {

    @Override
    @Lock(LockType.READ)
    public Interpreter createInstance() {
        Interpreter interpreter = Interpreter.getInstance();
        if (interpreter == null) {
            interpreter = Interpreter.createInstance();
        }
        return interpreter;
    }

}
