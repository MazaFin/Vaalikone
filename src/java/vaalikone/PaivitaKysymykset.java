/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Kysymykset;

/**
 *
 * @author Sami1531
 */
public class PaivitaKysymykset extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int kLKM;
        int kID;
        String kArvo;

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");


        try {

            //Listan pituus edelliseltä sivulta.
            kLKM = Integer.parseInt(request.getParameter("kLKM"));

            for (int i = 1; i <= kLKM; i++) {
                out.print("jee" + "<br>");
                // Edellisen sivun arvot muuttujiin.

                kID = Integer.parseInt(request.getParameter("q" + i));

                out.print("jee" + "<br>");
                kArvo = request.getParameter("K" + i);
                out.print(kID + "<br>");
                // Kysymys luokan olio luodaan jolla etsitään haluttu rivi tietokannasta
                Kysymykset kysymys = em.find(Kysymykset.class, kID);
                out.print(kID + "<br>");
                //Aloitetaan tietojen kirjaus
                em.getTransaction().begin();
                em.persist(kysymys);
                //Kirjataan kysymyksen arvo
                kysymys.setKysymys(kArvo);

                //Vahvistaa tapahtuman
                em.getTransaction().commit();

            }

        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            //"Tyhjennetään nykyinen sessio, jotta kysymysten uusi määrä saadaan haettua oikein
            session.invalidate();

            // Uudelleen ohjaus
            request.getRequestDispatcher("/prosessoitu-kysymykset.jsp").forward(request, response);
            out.close();

        }
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
