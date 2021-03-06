<%-- 
    Document   : ReviewEstimate
    Created on : May 2, 2019, 12:21:19 PM
    Author     : Simon Asholt Norup
--%>

<%@page import="logic.SVG.SVGDrawerFromSide"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="logic.SVG.SVGDrawerFromAbove"%>
<%@page import="data.help_classes.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Johannes Fog Carporte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../CSS/main.css">
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <div class="mainbody">
            <form>

                <%
                    Offer offer = (Offer) request.getSession().getAttribute("estimate");
                    Request r = offer.getRequest();
                    Carport carport = r.getCarport();
                    Roof roof = carport.getRoof();
                    Shed shed = carport.getShed();
                %>
                <h1>Overblik</h1>

                <div  style="display: inline-block; float:right">
                    <h4>Skitse fra oven</h4>
                    <%
                        String above = (String) request.getAttribute("SVGabove");
                        out.print(above);
                    %>    
                    <h4>Skitse fra siden</h4>
                    <%
                        String side = (String) request.getAttribute("SVGside");
                        out.print(side);
                    %>
                </div>  
                <br>

                <h3>Specifikationer</h3>
                <h4><b>Mål:</b> <%=carport.getLength()%>x<%=carport.getWidth()%>x<%=carport.getHeight()%></h4>
                <h4>
                    <% if (roof.getRaised()) {%>
                    <b>Tag med rejsning:</b> <%=roof.getType()%>, <%=roof.getSlope()%> graders hældning
                    <% } else {%>
                    <b>Fladt tag:</b> <%=roof.getType()%>
                    <% } %>
                </h4>

                <% if (shed != null) {%>
                <h4><b>Med redskabsskur:</b> <%=shed.getLength()%>x<%=shed.getWidth()%>, <br>Vægbeklædning af typen: <i><%=shed.getWallCoverings()%></i></h4>
                    <% }%>
                <br>

                <h3>Personlige oplysninger:</h3>
                <input placeholder="Fornavn" maxlength="50" type="text" name="firstname" required="required" pattern="[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*"><br>
                <input placeholder="Efternavn" type="text" name="lastname" required="required" pattern="[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*"><br>
                <br>
                <input placeholder="Adresse" maxlength="100" type="text" name="address" required="required" pattern="[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]+\s\d+"><br>
                <input placeholder="Postnummer" maxlength="4" type="text" name="zipcode" required="required" pattern="\d{4}"><br>
                <input placeholder="By" type="text" maxlength="50"  name="city" required="required" pattern="[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*"><br>
                <br>
                <input placeholder="Telefon" maxlength="8" type="text" name="phone" required="required" pattern="\d{8}"><br>
                <input placeholder="Email" maxlength="200" type="email" name="email" required="required"><br>
                <br>
                <textarea placeholder="Eventuelle kommentarer" maxlength="200" name="comments" cols="40" rows="5" style="resize: none;"></textarea><br>
                <br>

                <%
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    String num = numberFormat.format((offer.getPrice()));
                %>

                <h3>Estimeret Pris:<b><%=num%>,- DKK</b></h3>


                <button class="btn btn-primary btn-lg" type="submit" formaction="/FogProject/c/SendInformation">Send forespørgsel</button>
                <br><br>
                <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/CarportDetails';">Tilbage</button>
            </form>
        </div>
    </body>
</html>
