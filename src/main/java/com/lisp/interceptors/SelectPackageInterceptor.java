/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lisp.interceptors;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Packages;

/**
 *
 * @author Liferay
 */
@SelectPackage
@Interceptor
public class SelectPackageInterceptor implements Serializable {

    private static final long serialVersionUID = -4645422698404527287L;

    @AroundInvoke
    public Object selectPackage(InvocationContext context) throws Exception {
        Package homePackage;
        Object[] parameters = context.getParameters();

        for (Object param : parameters) {
            if (param instanceof Package) {
                Package p = (Package) param;
                homePackage = Packages.findPackage(p.getName());

                if (homePackage != null) {
                    Interpreter.evaluate("(in-package :" + homePackage.getName() + ")");
                }
                break;
            }
        }
        return context.proceed();
    }

}
