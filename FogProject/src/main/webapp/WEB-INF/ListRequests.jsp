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
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <h2>Følgende forespørgsler venter på at blive behandlet:</h2>
        
        <div class="panel panel-default">
            <div class="panel-body">
                <table border = "1">
                    <tr>
                        <td> Forespørgsel modtaget </td>
                        <td> Kundenavn </td>
                        <td> Tilbud afsendt </td>
                        <td>  </td>
                    </tr>
                    <%
                        HashMap<Request, Customer> requests = (HashMap<Request, Customer>) request.getAttribute("requests");

                        for (Map.Entry<Request, Customer> entry : requests.entrySet()) {
                            Request r = entry.getKey();
                            Customer c = entry.getValue();
                    %>
                    <tr>
                        <td><%=r.getSent().toString()%></td>
                        <td><%=c.getFullName()%></td>

                        <td> Nej </td>

                        <td>
                            <form action="/FogProject/c/RequestDetails" method=POST>
                                <input type="hidden" name="r_id" value="<%=r.getID()%>"/>
                                <button type="submit"> Detaljer </button>
                            </form>
                        </td>
                    </tr>
                    <%  }%>
                </table>
                <br>
                <button onclick="window.location.href = '/FogProject/c/ListRequests';">Tilbage</button>
            </div>
        </div>
    </body>
</html>
