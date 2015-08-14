package com.lisp.utils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author Akis
 */
@RequestScoped
public class UtilsProducer implements Serializable {

    private static final long serialVersionUID = -8745777242578241981L;

    /**
     * @return FacesContext Injectable (RequestScoped)
     */
    @Produces
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * @return SecureRandom as String Injectable (RequestScoped)
     */
    @Produces
    public SecureRandom getSecureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilsProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
