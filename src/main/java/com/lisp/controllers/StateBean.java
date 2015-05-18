package com.lisp.controllers;

import com.lisp.services.StatelessExecuteBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.JavaObject;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.LispThread;
import org.armedbear.lisp.scripting.AbclScriptEngine;
import org.armedbear.lisp.scripting.AbclScriptEngineFactory;

/**
 *
 * @author Liferay
 */
@Named(value = "stateBean")
@SessionScoped
public class StateBean implements Serializable {

    @EJB
    private SFSessionBean sFSessionBean;

    private static final long serialVersionUID = -4603114293916693579L;

    private String command;

    public StateBean() {
    }

    public String executeCommand() {

        sFSessionBean.service(command);

        return "";//String.valueOf(result.cdr().intValue());
    }
//------------------------------------------------------------------------------

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
