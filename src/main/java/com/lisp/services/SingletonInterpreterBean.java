package com.lisp.services;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Package;

/**
 *
 * @author Akis
 */
@Singleton
public class SingletonInterpreterBean implements SingletonInterpreterBeanLocal {

    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;

    private Interpreter interpreter;

    @PostConstruct
    private void init() {
        interpreter = singletonStartupBean.getInstance();
    }

    @Override
    @Lock(LockType.WRITE)
    @AccessTimeout(unit = TimeUnit.SECONDS, value = 4)
    public String executeCommand(String user, String command) {
        String result = "";
        Package userPackage = Packages.findPackage(user);
        if (userPackage == null) {
            return "## Error ##  Package not found !!";
        }

        try {
            interpreter.eval("(in-package :" + user + ")");
            LispObject object = interpreter.eval(command);
            result = object.princToString();
        } catch (Throwable t) {
            System.out.println("#######    EXCEPTION     ######");
            System.out.println(t.getMessage());
        }
        return result;
    }
}
