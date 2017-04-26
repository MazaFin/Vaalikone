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
public class EhdokkaanTiedot extends HttpServlet {

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
    //hae java logger-instanssi
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();



        // hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
        HttpSession session = request.getSession(true);

        //hae käyttäjä-olio http-sessiosta
        Kayttaja usr = (Kayttaja) session.getAttribute("usrobj");

        //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
        if (usr == null) {
            usr = new Kayttaja();
            logger.log(Level.FINE, "Luotu uusi käyttäjä-olio");
            session.setAttribute("usrobj", usr);

        }

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();


        try {

            //Integer syotettytunnus = Integer.parseInt(request.getParameter("Ehdokastunnus"));
            String syotettytunnus = request.getParameter("Ehdokastunnus").toString();
            String syotettytunniste = request.getParameter("Tunniste").toString();
            String Virhe = "Virheellinen käyttäjätunnus tai salasana";

            //Haetaan ehdokas tietokannasta.
            Query tunniste = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=" + syotettytunnus);
            List<Ehdokkaat> eTunniste = tunniste.getResultList();


            for (Ehdokkaat Tieto : eTunniste) {


                if (syotettytunnus.equals(Tieto.getEhdokasId().toString()) && syotettytunniste.equals(Tieto.getEtunimi())) {

                    // TÄHÄN TULIS SIT VARMAAN MATIN KOODI-->

                    //Haetaan ehdokas tietokannasta.
                    Query kysely = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=" + syotettytunnus);

                    List<Ehdokkaat> ehdokasList = kysely.getResultList();

                    //Hae haluttu kysymys tietokannasta
                    Query q = em.createQuery("SELECT k FROM Kysymykset k");
                    //q.setParameter(1, kysymys_id);

                    //Lue haluttu kysymys listaan
                    List<Kysymykset> kysymysList = q.getResultList();

                    //Asetetaan attribuutit listoille ja lähetetään eteenpäin.
                    usr.setEhdokasID(Integer.parseInt(syotettytunnus));
                    request.setAttribute("Ehd", ehdokasList);
                    request.setAttribute("kysymykset", kysymysList);
                    request.getRequestDispatcher("EhdokasTiedot.jsp").forward(request, response);
                    // <--MATIN KOODI

                } else {
                    // REDIRECT
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
