<%-- 
    Document   : CarportDetailsChange
    Created on : May 15, 2019, 11:58:16 AM
    Author     : Simon Asholt Norup
--%>

<%@page import="java.util.LinkedList"%>
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
            <h2>Kunder har følgende muligheder:</h2>
            <%
                LinkedList<Integer> lengths = (LinkedList<Integer>) request.getAttribute("lengths");
                LinkedList<Integer> widths = (LinkedList<Integer>) request.getAttribute("widths");
                LinkedList<String> roofsFlat = (LinkedList<String>) request.getAttribute("roofsFlat");
                LinkedList<String> roofsRaised = (LinkedList<String>) request.getAttribute("roofsRaised");
                LinkedList<Integer> roofSlopes = (LinkedList<Integer>) request.getAttribute("roofSlopes");
                LinkedList<Integer> shedLengths = (LinkedList<Integer>) request.getAttribute("shedLengths");
                LinkedList<Integer> shedWidths = (LinkedList<Integer>) request.getAttribute("shedWidths");
                LinkedList<String> shedCoverings = (LinkedList<String>) request.getAttribute("shedCoverings");
            %>
            <br>
            <form method = POST>
                <select id="types" name="type" style="display: block" onchange="switchType();">
                    <option selected disabled>Vælg type af muligheder</option>
                    <option value="length">Længder</option>
                    <option value="width">Bredder</option>
                    <option value="roofsFlat">Flade tagtyper</option>
                    <option value="roofsRaised">Rejste tagtyper</option>
                    <option value="roofSlopes">Taghældninger</option>
                    <option value="shedLength">Længder for skur</option>
                    <option value="shedWidth">Bredder for skur</option>
                </select>
                <br><br>
                <textarea>
                    
                </textarea>
            </form>
        </div>
    </body>
</html>
