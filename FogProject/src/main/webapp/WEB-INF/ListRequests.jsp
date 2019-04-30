<%-- 
    Document   : ListRequests
    Created on : Apr 25, 2019, 12:34:17 PM
    Author     : Simon Asholt Norup
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="data.help_classes.Customer"%>
<%@page import="java.util.LinkedList"%>
<%@page import="data.help_classes.Request"%>
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
        <h2>Følgende forespørgsler venter på at blive behandlet:</h2>
        
        <div class="panel panel-default">
            <div class="panel-body">
                <table border = "1">
                    <tr>
                        <td> ID </td>
                        <td> Forespørgsel modtaget </td>
                        <td> Kundenavn </td>
                        <td> Tilbud afsendt </td>
                        <td>  </td>
                    </tr>
                    <%
                        LinkedList<Request> requests = (LinkedList<Request>) request.getAttribute("requests");

                        for (Request r : requests) {
                    %>
                    <tr>
                        <td><%=r.getId()%></td>
                        <td><%=r.getSent().toString()%></td>
                        <td><%=r.getCustomer().getFullName()%></td>

                        <td> Nej </td>

                        <td>
                            <form action="/FogProject/c/RequestDetails" method=POST>
                                <input type="hidden" name="r_id" value="<%=r.getId()%>"/>
                                <button type="submit"> Detaljer </button>
                            </form>
                        </td>
                    </tr>
                    <%  }%>
                </table>
                <br>
                <button onclick="window.location.href = '/FogProject/c/CarportDetails';">Tilbage</button>
            </div>
        </div>
    </body>
</html>
