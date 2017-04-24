/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vaalikone.Kayttaja;
import vaalikone.Loki;

/**
 *
 * @author Matti
 */
public class M_LisaaVastaus extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();


        //hae parametrina tuotu edellisen kysymyksen vastaus

        int EhdokkaanVastaus = parseInt(request.getParameter("vastaus"));
        int KysymyksenNro = parseInt(request.getParameter("q"));
        //String KysymyksenNro = request.getParameter("q");
        //String[] vastaukset = request.getParameterValues("vastaus");

        
            // do something with id, this is checkbox value
            //int EhdokkaanVastaus = parseInt(vastaus);
        



        try {

            // Lisataan uusi vastaus tietokantaan  
            em.getTransaction().begin(); // Aloitetaan tapahtumien kirjaaminen
            Vastaukset vastausOlio = new Vastaukset(usr.getId(),KysymyksenNro ); //Luodaan vastaukset-luokan olio, parametrina annetaan ehdokkaan id ja kysymyksen id
            em.persist(vastausOlio); // Tehdään oliosta "hallittu", jolloin yhteys tietokantaan on kunnossa
            vastausOlio.setVastaus(EhdokkaanVastaus);  // Vastaus kysymykseen väliltä 1-5
            vastausOlio.setKommentti("Matin testikommentti"); //Kommentti vastauksesta
            em.getTransaction().commit(); // Vahvistetaan tapahtumat, tiedot kirjoitetaan tietokantaan 

        } finally {
            // Sulje tietokantayhteys
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
        
        //request.setAttribute("EhdokkaanID", 5);
        request.getRequestDispatcher("/M_HaeKysymykset")
                            .forward(request, response);
         
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
