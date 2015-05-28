package com.lisp.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.JavaObject;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Symbol;

/**
 *
 * @author Liferay
 */
@Stateful
public class StatefulUserBean implements StatefulUserBeanLocal {

    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;

    @Resource
    private SessionContext context;

    private Package homePackage;
    private Package globalPackage;

    @PostConstruct
    public void init() {

        globalPackage = singletonStartupBean.getCommonLispPackage();

        homePackage = Packages.createPackage(String.valueOf(context.hashCode()));
        homePackage.usePackage(globalPackage);

        //interpreter.eval("(defpackage :" + context.hashCode() + " (:use :common-lisp))");
    }

    @Override
    public String executeCommand(String command) {

        //JavaObject object = new JavaObject(command);
        //LispObject result = homePackage.execute(object);
        String result = singletonStartupBean.executeCommand(
                String.valueOf(context.hashCode()), command);

        return result;
    }
}
