package com.lisp.controllers;

import com.lisp.services.StatelessExecuteBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Liferay
 */
@Named(value = "executeBean")
@RequestScoped
public class ExecuteBean {

    @EJB
    private StatelessExecuteBeanLocal statelessExecuteBean;

    private String command;

    public ExecuteBean() {
    }

    public void execute() {
        String result = statelessExecuteBean.executeCommand(command);
        System.out.println(result);
    }

//--------------------------- Getters/Setters ---------------------------
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
