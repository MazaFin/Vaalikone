/*
 * To change this template, choose Tools | Templates
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
import persist.Vastaukset;

/**
 *
 * @author Sami1531
 */
public class VastausKasittely extends HttpServlet {

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

        int ehdokasID; // Ehdokkaan ID
        int kLKM; // Kysymysten LKM
        String vastausArvo; // Ehdokkaan vastauksen arvo
        String eKommentti; // Ehdokkaan vastauksen kommentti

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        try {

            //Muuttujille arvot edelliseltä sivulta
            ehdokasID = usr.getEhdokasID();
            kLKM = Integer.parseInt(request.getParameter("kysymysLKM"));

            for (int i = 1; i <= kLKM; i++) {
                em.getTransaction().begin(); // Aloitetaan tapahtumien kirjaaminen

                vastausArvo = request.getParameter("Vastaus" + i);
                eKommentti = request.getParameter("eKommentti" + i);

                Vastaukset vastausOlio = new Vastaukset(ehdokasID, i); //Luodaan vastaukset-luokan olio, parametrina annetaan ehdokkaan id ja kysymyksen id
                em.persist(vastausOlio); // Tehdään oliosta "hallittu", jolloin yhteys tietokantaan on kunnossa
                vastausOlio.setVastaus(Integer.parseInt(vastausArvo));  // Vastaus kysymykseen väliltä 1-5
                vastausOlio.setKommentti(eKommentti); //Kommentti vastauksesta
                em.getTransaction().commit(); // Vahvistetaan tapahtumat, tiedot kirjoitetaan tietokantaan
            }

        } finally {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                //tyhjennetään sessio, jotta listat alustuvat uudestaan
                session.invalidate();
            }
            request.getRequestDispatcher("prosessoitu-vastaukset.jsp").forward(request, response);
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
