package com.lisp.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import org.armedbear.lisp.Package;

/**
 *
 * @author Liferay
 */
@Stateful
public class StatefulUserBean implements StatefulUserBeanLocal {

    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;

    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;

    @Resource
    private SessionContext context;

    private Package homePackage;

    @PostConstruct
    public void init() {
        homePackage = singletonStartupBean.createPackage(String.valueOf(context.hashCode()));
    }

    @Override
    public String executeCommand(String command) {
        String result = singletonInterpreterBean.executeCommand(homePackage, command);

        return result;
    }
}
