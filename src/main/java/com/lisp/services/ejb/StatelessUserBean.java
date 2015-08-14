package com.lisp.services.ejb;

import java.security.SecureRandom;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.armedbear.lisp.Package;

/**
 *
 * @author Akis
 */
@Stateless
public class StatelessUserBean implements StatelessUserBeanLocal {

    @EJB
    private SingletonStartupBeanLocal singletonStartupBean;

    @Inject
    SecureRandom secureRandom;

    @Override
    public void logout() {
        // removePackage();
        // TODO: Invalidate HttpSession
    }

    @Override
    public Package getHomePackage() {
        Package homePackage = singletonStartupBean.createPackage(String.valueOf(Math.abs(secureRandom.nextLong())));
        return homePackage;
    }
}
