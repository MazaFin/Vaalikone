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
    int kLKM = kysymykset.size();


    for (Ehdokkaat Tieto : EhdokasTiedot) {


%>        
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

            <div class="content">
                <div class="kysymys">
                    <div class="tiedot">
                        <p>Numero: <%=Tieto.getEhdokasId()%></p>
                        <p>Nimi: <%=Tieto.getEtunimi()%> <%=Tieto.getSukunimi()%></p>
                        <p>Puolue: <%=Tieto.getPuolue()%></p>
                    </div>
                    <hr>
                    <h3>Kysymykset</h3>
                    <small>1=Täysin eri mieltä. 2=Osittain eri mieltä. 3=En osaa sanoa. 4=Osittain samaa mieltä. 5=Täysin samaa mieltä.</small>
                    <%
                        }

                        for (Kysymykset k : kysymykset) {

                    %> 

                    <p><%=k.getKysymysId()%> . <%=k.getKysymys()%></p>

                    <form onsubmit="return confirm('Lähetetäänkö vastaukset')" action="VastausKasittely" method="POST">


                        <%-- Luodaan vastausslider --%>
                        1 <input name="Vastaus<%=k.getKysymysId()%>" type="range" min="1" max="5" value="3" step="1" list="steplist" onchange="showValue(this.value)" /> 5
                        <%-- Vastausslider arvot datalistiin --%>
                        <datalist id="steplist">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </datalist>
                        <%-- Näyttää arvon vedettäessä slideriä (Toimii vissiin vain IE?) // TODO karsitaanko? --%>
                        <script type="text/javascript">
                        function showValue(newValue)
                        {
                            document.getElementById("range").innerHTML = newValue;
                        }
                        </script>
                        <br>
                        <div class="kommenttik">
                            <textarea name="eKommentti<%=k.getKysymysId()%>" class="kommenttikentta" maxlength="250" placeholder="Haluatko kommentoida? max 250 merkkiä."></textarea></div>

                        <%-- Kuljetetaan pari parametria seuraavalle sivulle käsittelyä varten --%> 
                        <input type="hidden" name="q" value="<%= k.getKysymysId()%>">
                        <input type="hidden" name="kysymysLKM" value="<%=kLKM%>">

                        <%
                            }
                        %>

                        <input type="submit" id="submitnappi" value="Vastaa" />
                    </form>
                </div>
            </div>
            <div class="footer">

                <div>
                    <p class="pull">
                        <a id="takas-ylos" href="#">Takaisin ylös</a>
                    </p>
                </div>
            </div>
        </div>

    </body>
</html>