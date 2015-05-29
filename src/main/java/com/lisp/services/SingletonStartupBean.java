package com.lisp.services;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.armedbear.lisp.Interpreter;
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

    private Package commonLispPackage;

    @PostConstruct
    private void init() {
        getInstance();
        commonLispPackage = Packages.findPackage(commonLispDefaultPackage);
    }

    @Override
    @Lock(LockType.READ)
    public Interpreter getInstance() {
        Interpreter interpreter = Interpreter.getInstance();
        if (interpreter == null) {
            interpreter = Interpreter.createInstance();
        }
        return interpreter;
    }

    @Override
    @Lock(LockType.WRITE)
    public Package createPackage(String user) {
        Package newPackage = Packages.findPackage(user);
        if (newPackage == null) {
            newPackage = Packages.createPackage(user);
            newPackage.usePackage(commonLispPackage);
        }
        return newPackage;
    }

    @Override
    @Lock(LockType.WRITE)
    public void removePackage(Package pack) {
        Package removePackage = Packages.findPackage(pack.getName());
        if (removePackage != null) {
            Packages.deletePackage(removePackage);
        }
    }

    @Override
    @Lock(LockType.READ)
    public Package findPackage(String packageName) {
        Package searchPackage = Packages.findPackage(packageName);
        return searchPackage;
    }

}
