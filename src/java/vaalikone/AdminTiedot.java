/*
 * To change this template, choose Tools | Templates
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Ehdokkaat;
import persist.Kysymykset;

/**
 *
 * @author Sami1531
 */
public class AdminTiedot extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {

            //Integer syotettytunnus = Integer.parseInt(request.getParameter("Ehdokastunnus"));
            String syotettytunnus = request.getParameter("Ehdokastunnus").toString();
            String syotettytunniste = request.getParameter("Tunniste").toString();

            //Haetaan ehdokas tietokannasta.
            Query tunniste = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=" + syotettytunnus);
            List<Ehdokkaat> eTunniste = tunniste.getResultList();

            for (Ehdokkaat Tieto : eTunniste) {

                if (syotettytunnus.equals(Tieto.getEhdokasId().toString()) && syotettytunniste.equals(Tieto.getEtunimi())) {

                    //Hae kaikki kysymykset tietokannasta
                    Query q = em.createQuery("SELECT k FROM Kysymykset k");

                    //Lue kaikki kysymykset listaan
                    List<Kysymykset> kysymysList = q.getResultList();

                    //Asetetaan attribuutit listoille ja lähetetään eteenpäin.
                    request.setAttribute("kysymykset", kysymysList);
                    request.getRequestDispatcher("Hallintapaneeli.jsp").forward(request, response);

                } else {
                    loginFailed(request, response);
                }
            }
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
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

    protected void loginFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String Virhe = "Virheellinen käyttäjätunnus tai salasana";

        request.setAttribute("Virhe", Virhe);
        try {
            request.getRequestDispatcher("ELogin.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(EhdokkaanTiedot.class.getName()).log(Level.SEVERE, null, ex);
        }
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
