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
                <small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small>
                <%
                    }
                    for (Kysymykset k : kysymykset) {
                %> 

                <p><%=k.getKysymysId()%> . <%=k.getKysymys()%></p>
                <form action="VastausKasittely">
                    
                    <select name="Vastaus<%=k.getKysymysId()%>">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <input type="hidden" name="q" value="<%= k.getKysymysId()%>">


                    <%
                        }
                    %>

                    <input type="submit" id="submitnappi" value="Vastaa" />
                </form>
            </div>
        </div>

    </body>
</html>