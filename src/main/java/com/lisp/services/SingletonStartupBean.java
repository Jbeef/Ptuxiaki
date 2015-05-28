package com.lisp.services;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Package;

/**
 *
 * @author Liferay
 */
@Startup
@Singleton
public class SingletonStartupBean implements SingletonStartupBeanLocal {

    private static final String commonLispDefaultPackage = "COMMON-LISP";

    private Interpreter interpreter;
    private Package commonLispPackage;

    @PostConstruct
    public void init() {
        getInstance();
    }

    @Override
    @Lock(LockType.READ)
    public Interpreter getInstance() {
        interpreter = Interpreter.getInstance();
        if (interpreter == null) {
            interpreter = Interpreter.createInstance();
        }
        return interpreter;
    }

    @Override
    @Lock(LockType.READ)
    public Package getCommonLispPackage() {
        if (commonLispPackage == null) {
            commonLispPackage = Packages.findPackage(commonLispDefaultPackage);
        }
        return commonLispPackage;
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
            getInstance().eval("(in-package :" + user + ")");
            LispObject object = getInstance().eval(command);
            result = object.princToString();
        } catch (Throwable t) {
            System.out.println("#######    EXCEPTION     ######");
            System.out.println(t.getMessage());
        }
        return result;
    }

}
