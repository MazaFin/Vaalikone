<%-- 
    Document   : ELogin
    Created on : Apr 18, 2017, 12:50:37 PM
    Author     : Sami1531
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

<div id="container">
<img id="headerimg" src="images/Logo.png" width="500" height="144" alt=""/>

<div class="kysymys">
    <form action="EhdokasTiedot.jsp">
        <p>Anna henkilökohtainen ehdokastunnus</p>
        <input type="text" name="Ehdokastunnus" value="">
        <br>
        <input id="Aloita-btn" type="submit" value="Aloita">
    </form>
</div>




</div>

</body>
</html>
