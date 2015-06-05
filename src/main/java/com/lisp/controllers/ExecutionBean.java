package com.lisp.controllers;

import com.lisp.services.SingletonInterpreterBeanLocal;
import com.lisp.services.SingletonStartupBeanLocal;
import com.lisp.services.StatefulUserBeanLocal;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Symbol;

/**
 *
 * @author Liferay
 */
@Named(value = "executionBean")
@SessionScoped
public class ExecutionBean implements Serializable {

    @EJB
    private StatefulUserBeanLocal statefulUserBean;
    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;
    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;

    private static final long serialVersionUID = 2308950136069917949L;

    private String command;
    private Package homePackage;    
    
    public ExecutionBean() {
    }

    @PostConstruct
    public void init() {
        homePackage = statefulUserBean.getHomePackage();
    }

    public void execute() {
        String result = singletonInterpreterBean.executeCommand(homePackage, command);
        System.out.println("> " + command + "\n" + result);
    }

    public void allPackages() {
        List<Package> all = singletonStartupBean.getAllPackages();
        for (Package p : all) {
            System.out.println(p.getName());
        }
        System.out.println("###  " + all.size());
    }

    public void executeFromFile() {

    }
    
    public void getSymbolsFromFile(){
        String url = "C:/Users/Liferay/Desktop/file.lisp";
        List<Symbol> list = singletonInterpreterBean.getSymbolsFromFile(homePackage, url);
        System.out.println(list.size());
        for(Symbol s : list) {
            System.out.println(s.getName());                
            System.out.println(s.getPropertyList().length());
            System.out.println(s.getParts().length());
        }
    }        
    
    public void unintern() { 
        // TODO homePackage.unintern(Symbol s);
    }
    
    public String logout() {
        statefulUserBean.logout();
        return ""; // TODO return to other page after logout 
    }

//--------------------------- Getters/Setters ---------------------------
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
