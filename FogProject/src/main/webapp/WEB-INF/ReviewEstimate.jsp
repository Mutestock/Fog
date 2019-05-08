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
        <div style="padding: 20px;">
            <form>

                <%
                    Offer offer = (Offer) request.getSession().getAttribute("estimate");
                    Request r = offer.getRequest();
                    Carport carport = r.getCarport();
                    Roof roof = carport.getRoof();
                    Shed shed = carport.getShed();
                    Customer customer = r.getCustomer();
                %>
                <h1>Overblik</h1>
                <div  style="display: inline-block; float:right">

                    <h4>Fornavn</h4>
                    <input type="text" name="firstname" required="required">
                    <h4>Efternavn</h4>
                    <input type="text" name="lastname" required="required">
                    <h4>Adresse</h4>
                    <input type="text" name="address" required="required">
                    <h4>Postnummer</h4>
                    <input type="text" name="zipcode" required="required">
                    <h4>By</h4>
                    <input type="text" name="city" required="required">
                    <h4>Telefon</h4>
                    <input type="text" name="phone" required="required">
                    <h4>Email</h4>
                    <input type="text" name="email" required="required">
                    <h4>Kommentarer</h4>
                    <input type="text" name="comments" >
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
                <h4><b>Med redskabsskur:</b> <%=shed.getLength()%>x<%=shed.getWidth()%>, vægbeklædning af typen <%=shed.getWallCoverings()%></h4>
                <% }%>

                
                <h4>Skitse ovenfra</h4>
                <%
                    SVGDrawerFromAbove SVGdrawer1 = new SVGDrawerFromAbove();
                    String above = SVGdrawer1.drawCarportFlatRoof(carport);
                    out.print(above);
                %>    
                <h4>Skitse sidefra</h4>
                <%
                    SVGDrawerFromSide SVGdrawer2 = new SVGDrawerFromSide();
                    String side = SVGdrawer2.drawCarportFlatRoofSide(carport);
                    out.print(side);
                %>


                <h3>Kommentarer:</h3>
                <p><%=r.getComments()%></p>
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
