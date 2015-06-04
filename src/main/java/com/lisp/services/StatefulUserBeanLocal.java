package com.lisp.services;

import java.util.List;
import javax.ejb.Local;
import org.armedbear.lisp.Package;

/**
 *
 * @author Liferay
 */
@Local
public interface StatefulUserBeanLocal {

    String executeCommand(String command);

    List<Package> getAllPackages();
    
    void executeFromFile();
}
