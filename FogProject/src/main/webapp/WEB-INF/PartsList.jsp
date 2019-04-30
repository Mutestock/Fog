<%-- 
    Document   : PartsList
    Created on : 30-04-2019, 17:39:37
    Author     : Simon Asholt Norup
--%>

<%@page import="java.util.LinkedList"%>
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
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <%
            Request r = (Request) request.getSession().getAttribute("request");
            Carport carport = r.getCarport();
            Roof roof = carport.getRoof();
            Shed shed = carport.getShed();
        %>
        <h1>Stykliste</h1>
        <p><b>Forespørgsel ID:</b> <%=r.getId()%> <b>Afsendt:</b> <%=r.getSent().toString()%></p>

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
        <% } %>
        <br>

        <%
            PartsList partsList = (PartsList) request.getAttribute("partsList");

            LinkedList<data.help_classes.Part> currentList;
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        currentList = partsList.getWoodPackage();
                        break;
                    case 1:
                        currentList = partsList.getRoofPackage();
                        break;
                    default:
                        currentList = partsList.getFittingsAndScrews();
                        break;
                }
        %>
        <div class="panel panel-default">
            <div class="panel-body">
                <table border = "1">
                    <tr>
                        <td> <b>Vare</b> </td>
                        <td> <b>Længde</b> </td>
                        <td> <b>Antal</b> </td>
                        <td> <b>Beskrivelse</b> </td>
                    </tr>
                    <%
                        for (data.help_classes.Part part : currentList) {
                    %>
                    <tr>
                        <td><%=part.getName()%></td>
                        <td><%=part.getLength()%></td>
                        <td><%=part.getAmount()%></td>
                        <td><%=part.getDescription()%></td>
                    </tr>
                    <% } %>
                </table>
                <br>
            </div>
        </div>
        <% }%>
        <button onclick="window.location.href = '/FogProject/c/RequestDetails';">Tilbage</button>
    </body>
</html>
