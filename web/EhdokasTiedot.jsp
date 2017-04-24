<%-- 
    Document   : EhdokasTiedot
    Created on : Apr 18, 2017, 1:46:16 PM
    Author     : Sami1531
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<%@page import="java.util.List"%>

<!doctype html>

<%
    @SuppressWarnings("unchecked")
    List<Ehdokkaat> EhdokasTiedot = (List<Ehdokkaat>) request.getAttribute("Ehd");
    List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");

    for (Ehdokkaat Tieto : EhdokasTiedot) {


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

                <p>Numero: <%=Tieto.getEhdokasId()%></p>
                <p>Etunimi: <%=Tieto.getEtunimi()%></p>
                <p>Sukunimi: <%=Tieto.getSukunimi()%></p>
                <p>Puolue: <%=Tieto.getPuolue()%></p>
                
                <h3>Kysymykset</h3>

                <%
                    }
                    for (Kysymykset k : kysymykset) {
                %> 
                
                <p><%=k.getKysymysId()%> : <%=k.getKysymys()%></p>
                
                
                <%
                    }
                %>
                <form action="Vastaus.jsp">
                    <input id="submitnappi" type="submit" value="Aloita" name="btnAloita" />                   
                </form>
            </div>
        </div>

    </body>
</html>