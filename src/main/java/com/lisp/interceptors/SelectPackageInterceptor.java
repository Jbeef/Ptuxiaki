package com.lisp.interceptors;

import java.io.Serializable;
import java.util.logging.Logger;
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
    private static final Logger LOG = Logger.getLogger(SelectPackageInterceptor.class.getName());
    
    @AroundInvoke
    public Object selectPackage(InvocationContext context) throws Exception {
        Package homePackage = null;
        Object[] parameters = context.getParameters();
        
        for (Object param : parameters) {
            if (param instanceof Package) {
                Package p = (Package) param;
                homePackage = Packages.findPackage(p.getName());
                break;
            }
        }
        
        if (homePackage != null) {
            Interpreter.evaluate("(in-package :" + homePackage.getName() + ")");
            return context.proceed();
        } else {
            LOG.info("# SelectPackageInterceptor: Package not found in interceptor.");
            //throw new Exception("SelectPackageInterceptor: Package not found in interceptor.");
            return null;
        }
        
    }
    
}
