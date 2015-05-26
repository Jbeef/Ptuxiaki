package com.lisp.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;

/**
 *
 * @author Liferay
 */
@Stateful
public class StatefulUserBean implements StatefulUserBeanLocal {

    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;

    @Resource
    private SessionContext context;

    private Interpreter interpreter;

    @PostConstruct
    public void init() {
        System.out.println("Stateful init - HashCode: " + context.hashCode());
        interpreter = singletonInterpreterBean.createInstance();
        interpreter.eval("(defpackage :" + context.hashCode() + " (:use :common-lisp))");        
    }

    @Override
    public String executeCommand(String command) {

        interpreter.eval("(in-package :" + context.hashCode() + ")");
        LispObject result = interpreter.eval(command);
        return result.princToString();
    }
}
