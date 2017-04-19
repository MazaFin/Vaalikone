<%-- 
    Document   : EhdokasTiedot
    Created on : Apr 18, 2017, 1:46:16 PM
    Author     : Sami1531
--%>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<%@page import="persist.Ehdokkaat"%>
<%@page import="java.util.List"%>

<!doctype html>

<%
    int eID;
    eID = Integer.parseInt(request.getParameter("Ehdokastunnus")); // tarkistus puuttuu
    Ehdokkaat e = new Ehdokkaat(eID);


    String eEtunimi;
    eEtunimi = e.getEtunimi();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diginide Vaalikone 2.0</title>

        <link href="style.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <div id="container">
            <img id="headerimg" src="images/Logo.png" width="500" height="144" alt=""/>

            <div class="kysymys">

                <h2>Moikku</h2>
                <p><%= eID%></p>

                <p><%= eEtunimi%></p>






            </div>




        </div>

    </body>
</html>
