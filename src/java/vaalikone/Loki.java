/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Jonne
 */
public class Loki {
    
    private final static Logger logger = Logger.getLogger(Loki.class.getName());
    private static FileHandler fh = null;
    
    /**
     * Luo uusi java logger-instanssi 
     */
    public static void init(){
        try {
            fh=new FileHandler("vaalikoneLoki.log", false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.CONFIG);
    }
}