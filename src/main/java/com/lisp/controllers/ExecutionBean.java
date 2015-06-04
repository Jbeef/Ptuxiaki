package com.lisp.controllers;

import com.lisp.services.StatefulUserBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Package;

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

    public void allPackages() {
        List<Package> all = statefulUserBean.getAllPackages();
        for (Package p : all) {
            System.out.println(p.getName());
        }
        System.out.println("###  " + all.size());
    }

    public void executeFromFile(){
        statefulUserBean.executeFromFile();
    }

//--------------------------- Getters/Setters ---------------------------
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
