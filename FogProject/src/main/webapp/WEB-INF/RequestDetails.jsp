<%-- 
    Document   : RequestDetails
    Created on : Apr 26, 2019, 11:52:10 AM
    Author     : Simon Asholt Norup
--%>

<%@page import="logic.SVG.SVGDrawerFromSide"%>
<%@page import="logic.SVG.SVGDrawerFromAbove"%>
<%@page import="java.text.DecimalFormat"%>
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
        <link rel="stylesheet" type="text/css" href="../CSS/main.css">
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <div class="mainbody">
            <%
                Offer offer = (Offer) request.getSession().getAttribute("offer");
                Request r = offer.getRequest();
                Carport carport = r.getCarport();
                Roof roof = carport.getRoof();
                Shed shed = carport.getShed();
                Customer customer = r.getCustomer();
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

            <% String dateConv = (r.getSent().toString());
                String received = dateConv.replace("T", " ");
            %>
            <p><b>Forespørgsel ID:</b> <%=r.getId()%> <b>Afsendt:</b> <%=received%></p>
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
            <h4><b>Med redskabsskur:</b> <%=shed.getLength()%>x<%=shed.getWidth()%>, <br>Vægbeklædning af typen <i><%=shed.getWallCoverings()%></i></h4>
                <% }%>

            <h3>Personlige informationer</h3>
            <h4><b>Navn:</b> <%=customer.getFullName()%></h4>
            <h4><b>Adresse:</b> <%=customer.getAddress()+", "+customer.getZipcode()+" "+customer.getCity()%></h4>
            <h4><b>Telefon:</b> <%=customer.getPhone()%></h4>
            <h4><b>Email:</b> <%=customer.getEmail()%></h4>

            <h3>Kommentarer:</h3>
            <textarea cols="50" rows="5" readonly style="resize: none;"><%=r.getComments()%></textarea>
            <br><br>

            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/PartsList';">Se Stykliste</button>
            <br><br>

            <% if (r.hasReceivedOffer()) {%>
            <h3>Tilbud afsendt:</h3>
            <% dateConv = (offer.getSent().toString());
                String sent = dateConv.replace("T", " ");
            %>
            <p><%=sent%></p>
            <%
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                String price = numberFormat.format((offer.getPrice()));
                String shippingCosts = numberFormat.format((offer.getShippingCosts()));
            %>
            <p><b>Pris:</b> <%=price%>,- DKK</p>
            <p><b>Fragtomkostninger:</b> <%=shippingCosts%>,- DKK</p>
            <% } else {%>
            <form method = POST>
                <h4>Pris:</h4>
                <input name="price" type="number" step=".01" min="0" required="required" value="<%=offer.getPrice()%>"> <p style="display: inline-block;">,- DKK</p>
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
