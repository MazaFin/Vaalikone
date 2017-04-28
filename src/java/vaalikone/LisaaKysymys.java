/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Kysymykset;
import persist.Vastaukset;

/**
 *
 * @author Matti
 *
 * Tämä servlet lisää uuden rivin kysymykset tauluun
 */
public class LisaaKysymys extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //kysymysten lkm parametrina edelliseltä sivulta
        int kysymystenLKM = Integer.parseInt(request.getParameter("kmaara"));
        //int uusiID = kysymystenLKM + 1;

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);


        try {

            em.getTransaction().begin(); // Aloitetaan tapahtumien kirjaaminen

            Kysymykset kysymysOlio = new Kysymykset(); //Luodaan kysymykset-luokan olio

            kysymysOlio.setKysymysId(kysymystenLKM + 1); // uuden kysymyksen id
            //kysymysOlio.setKysymys("Matin testikysymys");

            em.persist(kysymysOlio); // Tehdään oliosta "hallittu", jolloin yhteys tietokantaan on kunnossa
            em.getTransaction().commit(); // Vahvistetaan tapahtumat, tiedot kirjoitetaan tietokantaan

        } finally {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                

            }
            //response.sendRedirect("Hallintapaneeli");
            
            //"Tyhjennetään nykyinen sessio, jotta kysymysten uusi määrä saadaan haettua oikein
            session.invalidate();
            request.getRequestDispatcher("/prosessoitu-LisaaKysymys.jsp").forward(request, response);
            out.close();
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
