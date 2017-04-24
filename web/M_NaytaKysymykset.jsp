<%-- 
    Document   : M_NaytaKysymykset
    Created on : Apr 20, 2017, 7:49:37 PM
    Author     : Matti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!doctype html>
<html>

    <script type="text/javascript">
<!--//

        function submitAll() {
            document.f1.action = "M_LisaaVastaus.java";
            document.f1.submit();
        }
//-->
    </script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diginide Vaalikone 2.0</title>

        <link href="style.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <div id="container">
            <img id="headerimg" src="images/Logo.png" width="500" height="144" alt=""/>


            <h3>Talla sivulla naytetaan kaikki kysymykset ja ehdokkaan tiedot</h3>
            <%
                @SuppressWarnings("unchecked")
                List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");
                List<Ehdokkaat> ehdokkaat = (List<Ehdokkaat>) request.getAttribute("ehdokkaat");

                // Kirjoitetaan haetut kysymykset ruudulle
                for (Kysymykset kysymys : kysymykset) {%>
            <div class="kysymys">
                <%= kysymys.getKysymysId()%> / 19 <br>
                <%= kysymys.getKysymys()%>
            </div>

            <form  action="M_LisaaVastaus" id="vastausformi">
                <label>1</label><input type="radio" name="vastaus" value="1" />
                <label>2</label><input type="radio" name="vastaus" value="2" />
                <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" />
                <label>4</label><input type="radio" name="vastaus" value="4" />
                <label>5</label><input type="radio" name="vastaus" value="5" />
                <input type="hidden" name="q" value="<%= kysymys.getKysymysId()%>">


                <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
                 <input type="submit" id="submitnappi" value="Tallenna vastaukset" />
            </form>
            <hr>
            <%

                }
            %>



           


            <%

                //Kirjoitetaan haetun ehdokkaan tiedot ruudulle
                for (Ehdokkaat ehdokas : ehdokkaat) {%>
            <div class="ehdokas">
                <p>Ehdokkaan id: <%=ehdokas.getEhdokasId()%> </p>
                <p>Ehdokkaan etunimi: <%=ehdokas.getEtunimi()%> </p>
                <p>Ehdokkaan sukunimi: <%=ehdokas.getSukunimi()%> </p>
                <p>Ehdokkaan kotipaikkakunta: <%=ehdokas.getKotipaikkakunta()%> </p> 
            </div>

            <%
                }
            %>





        </div>

    </body>
</html>
