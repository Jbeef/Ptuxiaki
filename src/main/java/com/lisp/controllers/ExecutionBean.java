package com.lisp.controllers;

import com.lisp.services.StatefulUserBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Liferay
 */
@Named(value = "executionBean")
@SessionScoped
public class ExecutionBean implements Serializable {

    private static final long serialVersionUID = 2308950136069917949L;

    @EJB
    private StatefulUserBeanLocal statefulUserBean;

    private String command;

    public ExecutionBean() {
    }

    public void execute() {
        String result = statefulUserBean.executeCommand(command);
        System.out.println("> " + command + "\n" + result);
    }

//--------------------------- Getters/Setters ---------------------------
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
