package vaalikone;


import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author Jonne
 */
public class ServletListener implements ServletContextListener {

    /**
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        EntityManagerFactory emf =
            createEntityManagerFactory("Vaalikone5PU");
        sce.getServletContext().setAttribute("emf", emf);
        
        Loki.init();

    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf =
            (EntityManagerFactory)sce.getServletContext().getAttribute("emf");
        emf.close();
    }
    
}
