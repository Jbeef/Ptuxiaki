/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lisp.services;

import javax.ejb.Local;
import org.armedbear.lisp.Interpreter;
import org.armedbear.lisp.Package;

/**
 *
 * @author Liferay
 */
@Local
public interface SingletonStartupBeanLocal {

    Interpreter getInstance();

    Package createPackage(String user);
    
    void removePackage(Package pack);
    
    Package findPackage(String packageName);
}
