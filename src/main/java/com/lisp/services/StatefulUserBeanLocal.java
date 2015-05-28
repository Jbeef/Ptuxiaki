package com.lisp.services;

import javax.ejb.Local;

/**
 *
 * @author Liferay
 */
@Local
public interface StatefulUserBeanLocal {
    
    String executeCommand(String command);
}
