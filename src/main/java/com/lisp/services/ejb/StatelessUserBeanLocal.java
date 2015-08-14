package com.lisp.services.ejb;

import javax.ejb.Local;
import org.armedbear.lisp.Package;

/**
 *
 * @author Akis
 */
@Local
public interface StatelessUserBeanLocal {

    public void logout();

    public Package getHomePackage();

}
