/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lisp.services;

import javax.ejb.Local;

/**
 *
 * @author Akis
 */
@Local
public interface SingletoninterpreterBeanLocal {

    String executeCommand(String user, String command);
}
