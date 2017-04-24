/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

/**
 *
 * @author Matti
 */
public class M_HaeKysymykset extends HttpServlet {

    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Loki.class.getName());
    //private int EhdokasNro = 2;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new Kayttaja();
            logger.log(Level.FINE, "Luotu uusi käyttäjä-olio");
            session.setAttribute("usrobj", usr);
            int EhdokasNro = parseInt(request.getParameter("EhdokkaanID"));
            usr.setId(EhdokasNro);
        }
        

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf
                = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {

            //haetaan kaikki kysymykset ja lähetetään ne listana eteenpäin
            Query q = em.createQuery(
                    "SELECT k FROM Kysymykset k");
            //luetaan kysymykset listaan
            List<Kysymykset> kaikkiKysymykset = q.getResultList();

            //Hae lista ehdokkaista
            Query qE = em.createQuery(
                    //"SELECT e FROM Ehdokkaat e "
                    "SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=?1"
            );
            
            //hae etusivulta parametrina tuotu ehdokkaan id
            //int EhdokasNro = parseInt(request.getParameter("EhdokkaanID"));
            
            // annetaan kyselylle parametrina muuttujan EhdokasNro -arvo
            //qE.setParameter(1, EhdokasNro);
            qE.setParameter(1, usr.getId());
            List<Ehdokkaat> kaikkiEhdokkaat = qE.getResultList();

            //lähetetään lista eteenpäin
            request.setAttribute("ehdokkaat", kaikkiEhdokkaat);
            request.setAttribute("kysymykset", kaikkiKysymykset);
            request.getRequestDispatcher("/M_NaytaKysymykset.jsp")
                    .forward(request, response);
            
            
            

        } finally {
            // Sulje tietokantayhteys
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
