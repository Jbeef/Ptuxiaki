package com.lisp.services;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    private SingletonStartupBeanLocal singletonStartupBean;

    @Resource
    private SessionContext context;

    private Package homePackage;

    @PreDestroy
    private void remove() {
        singletonStartupBean.removePackage(homePackage);
    }

    @Override
    public Package getHomePackage() {
        homePackage = singletonStartupBean.createPackage(String.valueOf(context.hashCode()));
        return homePackage;
    }

}
