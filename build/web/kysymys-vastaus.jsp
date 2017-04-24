<%-- 
    Document   : kysymys-vastaus
    Created on : Apr 20, 2017, 10:19:21 AM
    Author     : valtteri1403
--%>

<%@page import="java.util.List"%>
<%@page import="persist.Kysymykset"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Hae tietokantayhteys
    
    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
    EntityManager em = emf.createEntityManager();

    // Haetaan kaikki kysymykset tietokannasta:
    List<Kysymykset> kysymykset = em.createQuery("SELECT k FROM Kysymykset k").getResultList();
    
    //suljetaan
    em.close();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ehdokas - Kysymykset</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <% //haetaan kysymyksien määrä %>
            <img src="Logo.png" width="720" /><h1>Kysymyslista (<span><%= kysymykset.size() %></span> kysymystä)</h1>
            
            <hr>
            <div>
                
                <% //hakee kysymyksien määrän
                    for (int i = 0; i < kysymykset.size(); i++) {
                    Integer kysymys = kysymykset.get(i).getKysymysId();
                %>
                <div>
                    <div><% //näyttää kysymyksen
                        out.println(kysymys + ". " + kysymykset.get(i).getKysymys());
                        
                    %></div>
                    
                    <br>
                     <form action="Vaalikone" id="vastausformi">
                    <label>1</label><input type="radio" name="vastaus" value="1" />
                    <label>2</label><input type="radio" name="vastaus" value="2" />
                    <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" />
                    <label>4</label><input type="radio" name="vastaus" value="4" />
                    <label>5</label><input type="radio" name="vastaus" value="5" />
                 
                    <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
                    
                </form>
                    <hr>
                </div>
                <%}%>
            </div>
            <div>
                        <form action="tulokset.jsp" method="GET">
                            <input type="submit" id="submitnappi" value="Tallenna" />
                        </form>
                    </div>
            <br>
            <hr>
            <div>
            </div>
        </div>
           
    </body>

</html>
