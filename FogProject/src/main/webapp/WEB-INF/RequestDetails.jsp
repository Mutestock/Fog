<%-- 
    Document   : RequestDetails
    Created on : Apr 26, 2019, 11:52:10 AM
    Author     : Simon Asholt Norup
--%>

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
            <%
                Request r = (Request) request.getSession().getAttribute("request");
                Carport carport = r.getCarport();
                Roof roof = carport.getRoof();
                Shed shed = carport.getShed();
                Customer customer = r.getCustomer();
            %>
            <h1>Overblik</h1>
            <p><b>Forespørgsel ID:</b> <%=r.getId()%> <b>Afsendt:</b> <%=r.getSent().toString()%></p>
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
            <br>

            <h3>Kundeoplysninger:</h3>
            <p><b>Navn:</b> <%=customer.getFullName()%></p>
            <p><b>Adresse:</b> <%=customer.getAddress()%>, <%=customer.getZipcode()%> <%=customer.getCity()%></p>
            <p><b>Telefon:</b> <%=customer.getPhone()%></p>
            <p><b>Mail:</b> <%=customer.getEmail()%></p>
            <br>

            <h3>Kommentarer:</h3>
            <p><%=r.getComments()%></p>
            <br>

            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/PartsList';">Se Stykliste</button>
            <br><br>
            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/ListRequests';">Tilbage</button>
        </div>
    </body>
</html>
