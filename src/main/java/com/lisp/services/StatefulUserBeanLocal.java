package com.lisp.services;

import javax.ejb.Local;
import org.armedbear.lisp.Package;

/**
 *
 * @author Liferay
 */
@Local
public interface StatefulUserBeanLocal {

    Package getHomePackage();
}
