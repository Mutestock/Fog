<%-- 
    Document   : RequestDetails
    Created on : Apr 26, 2019, 11:52:10 AM
    Author     : Simon Asholt Norup
--%>

<%@page import="data.help_classes.*"%>
<%@page contentType="text/html" pageEncoding="Windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Windows-1252">
        <title>Johannes Fog Carporte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <!--<link rel="stylesheet" type="text/css" href="../CSS/main.css">-->
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <div style="padding: 20px;">
            <%
                Offer offer = (Offer) request.getSession().getAttribute("offer");
                Request r = offer.getRequest();
                Carport carport = r.getCarport();
                Roof roof = carport.getRoof();
                Shed shed = carport.getShed();
                Customer customer = r.getCustomer();
            %>
            <h1>Overblik</h1>
            <p><b>Foresp�rgsel ID:</b> <%=r.getId()%> <b>Afsendt:</b> <%=r.getSent().toString()%></p>
            <br>

            <h3>Specifikationer</h3>
            <h4><b>M�l:</b> <%=carport.getLength()%>x<%=carport.getWidth()%>x<%=carport.getHeight()%></h4>
            <h4>
                <% if (roof.getRaised()) {%>
                <b>Tag med rejsning:</b> <%=roof.getType()%>, <%=roof.getSlope()%> graders h�ldning
                <% } else {%>
                <b>Fladt tag:</b> <%=roof.getType()%>
                <% } %>
            </h4>
            <% if (shed != null) {%>
            <h4><b>Med redskabsskur:</b> <%=shed.getLength()%>x<%=shed.getWidth()%>, v�gbekl�dning af typen <%=shed.getWallCoverings()%></h4>
            <% }%>
           

            <h3>Kommentarer:</h3>
            <p><%=r.getComments()%></p>
            <br>

            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/PartsList';">Se Stykliste</button>
            <br><br>

            <% if (r.hasReceivedOffer()) {%>
            <h3>Tilbud afsendt:</h3>
            <p><%=offer.getSent()%></p>
            <p><b>Pris:</b> <%=offer.getPrice()%></p>
            <p><b>Fragtomkostninger:</b> <%=offer.getShippingCosts()%></p>
            <% } else {%>
            <form method = POST>
                <h4>Pris:</h4>
                <input name="price" type="number" step="0.01" min="0" required="required" value="<%=offer.getPrice()%>"> <p style="display: inline-block;">,- DKK</p>
                <h4>Fragtomkostninger:</h4>
                <input name="shippingCosts" type="number" step="0.01" min="0" required="required" value="<%=offer.getShippingCosts()%>"> <p style="display: inline-block;">,- DKK</p>
                <br><br>
                <button class="btn btn-primary btn-lg" type="submit" formaction="/FogProject/c/SendOffer">Send det endelige tilbud</button>


               

            </form>
            <% }%>
            <br>

            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/ListRequests';">Tilbage</button>
        </div>
    </body>
</html>
