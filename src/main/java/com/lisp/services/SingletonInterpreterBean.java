package com.lisp.services;

import com.lisp.interceptors.SelectPackage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Packages;
import org.armedbear.lisp.Package;

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
    @Lock(LockType.WRITE)
    @AccessTimeout(unit = TimeUnit.SECONDS, value = 4)
    public String executeCommand(Package homePackage, String command) {
        String result = "";

        try {
            LispObject object = interpreter.eval(command);
            result = object.princToString();
        } catch (Throwable t) {
            System.out.println("#######    EXCEPTION     ######");
            System.out.println(t.getMessage());
        }
        return result;
    }

}
