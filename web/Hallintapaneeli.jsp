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
            <div class="kysymys">
                <form onsubmit="return confirm('Oletko varma?')" action="index.html">
                    <%
                        @SuppressWarnings("unchecked")
                        List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");
                        for (Kysymykset kysymys : kysymykset) {%>

                    <%= kysymys.getKysymysId()%>.<input class="Kinput" type="text" name="K<%= kysymys.getKysymysId()%>" value="<%= kysymys.getKysymys()%>">
                    <br>
                    <%            }
                    %>
                    <input type="submit" id="submitnappi" value="Päivitä" />
                </form>
            </div>
        </div>
    </body>
</html>
