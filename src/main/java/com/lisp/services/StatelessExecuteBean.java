package com.lisp.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;

/**
 *
 * @author Liferay
 */
@Stateless
public class StatelessExecuteBean implements StatelessExecuteBeanLocal {

    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;

    private Interpreter interpreter;

    @PostConstruct
    public void init() {
        interpreter = singletonInterpreterBean.createInstance();
    }

    @Override
    public String executeCommand(String command) {

        LispObject result = interpreter.eval(command);
        return result.princToString();
    }

}
