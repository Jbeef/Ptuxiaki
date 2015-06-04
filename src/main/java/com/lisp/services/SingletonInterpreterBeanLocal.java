package com.lisp.services;

import java.util.List;
import javax.ejb.Local;
import org.armedbear.lisp.Package;
import org.armedbear.lisp.Symbol;

/**
 *
 * @author Akis
 */
@Local
public interface SingletonInterpreterBeanLocal {

    String executeCommand(Package homePackage, String command);
    
    String executeFromFile(Package homePackage);
    
    List<Symbol> getSymbolsFromFile(Package homePackage, String url);
}
