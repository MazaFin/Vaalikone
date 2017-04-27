<%-- 
    Document   : vastaus
    Created on : 09-Apr-2015, 12:50:47
    Author     : Jonne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diginide Vaalikone 2.0</title>

<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="container-et">
    <div class="header">
            <div class="kuva">
                <img id="headerimg" src="Logo.png" width="720" />
            </div>
            
           <div class="nav">
                        <ul class="nav-menu">
                            <li><a href="index.html">Etusivu</a></li>
                            <li><a href="ELogin.jsp">Ehdokas</a></li>
                            <li><a href="Vaalikone">Hae ehdokasta</a></li>
                            <li><a href="admin_login.jsp">Hallinta</a></li>
                        </ul> 
                    </div>
                </div>




 <%
            @SuppressWarnings("unchecked") 
            List<Kysymykset> kysymykset = (List<Kysymykset>)request.getAttribute("kysymykset");
            Number kysymystenlkm = (Number) request.getAttribute("kmaara");
            
            for (Kysymykset kysymys : kysymykset) { %>
            <div class="sisalto">
            <div class="kysymys">
                <%= kysymys.getKysymysId() %> / <%=kysymystenlkm.intValue()%> <br>
                <%= kysymys.getKysymys() %>
                 </div>
                <form action="Vaalikone" id="vastausformi">
                    <label>1</label><input type="radio" name="vastaus" value="1" />
                    <label>2</label><input type="radio" name="vastaus" value="2" />
                    <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" />
                    <label>4</label><input type="radio" name="vastaus" value="4" />
                    <label>5</label><input type="radio" name="vastaus" value="5" />
                    <input type="hidden" name="q" value="<%= kysymys.getKysymysId() %>">
                    <input type="submit" id="submitnappi" value="Vastaa" />
                </form>
                    <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
                <%
            } 
        %>


            </div>

</div>

</body>
</html>
