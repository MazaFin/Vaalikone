<%-- 
    Document   : Hallintapaneeli
    Created on : Apr 27, 2017, 9:04:58 AM
    Author     : Sami1531
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css">
        <title>Hallintapaneeli</title>
    </head>
    <body>
        <div id="container-et">
            <h2>Muokkaa kysymyksiä</h2>
            <div class="kysymys">
                <form onsubmit="return confirm('Oletko varma?')" action="PaivitaKysymykset" method="POST">
                    <%
                        @SuppressWarnings("unchecked")
                        List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");
                        int kLKM = kysymykset.size();
                        for (Kysymykset kysymys : kysymykset) {%>

                    <%= kysymys.getKysymysId()%>.<input class="Kinput" type="text" name="K<%= kysymys.getKysymysId()%>" value="<%= kysymys.getKysymys()%>">
                    <br>

                    <input type="hidden" name="q<%= kysymys.getKysymysId()%>" value="<%= kysymys.getKysymysId()%>">
                    <input type="hidden" name="kLKM" value="<%=kLKM%>">
                    <%            }
                    %>
                    <input type="submit" name="laheta" id="submitnappi" value="Päivitä" />
                </form>
                
            </div>
                    <a href="LisaaKysymys?kmaara=<%=kLKM%>">Lisää uusi kysymys</a> 
        </div>
    </body>
</html>
