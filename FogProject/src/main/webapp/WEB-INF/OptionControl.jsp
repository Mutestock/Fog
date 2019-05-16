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
                String type = (String) request.getAttribute("type");
                LinkedList<String> options = (LinkedList<String>) request.getAttribute("options");
            %>
            <br>
            <form method = POST formaction="/FogProject/c/OptionControl">
                <select id="types" name="type" onchange="this.form.submit()">
                    <option 
                        <%if (type == null) {%> selected <%}%>
                        disabled>Vælg type af muligheder</option>
                    <option value="length"
                            <%if (type != null && type.equals("length")) {%> selected <%}%>
                            >Længder</option>
                    <option value="width"
                            <%if (type != null && type.equals("width")) {%> selected <%}%>
                            >Bredder</option>
                    <option value="roofFlat"
                            <%if (type != null && type.equals("roofFlat")) {%> selected <%}%>
                            >Flade tagtyper</option>
                    <option value="roofRaised"
                            <%if (type != null && type.equals("roofRaised")) {%> selected <%}%>
                            >Rejste tagtyper</option>
                    <option value="roofSlope"
                            <%if (type != null && type.equals("roofSlope")) {%> selected <%}%>
                            >Taghældninger</option>
                    <option value="shedLength"
                            <%if (type != null && type.equals("shedLength")) {%> selected <%}%>
                            >Længder for skur</option>
                    <option value="shedWidth"
                            <%if (type != null && type.equals("shedWidth")) {%> selected <%}%>
                            >Bredder for skur</option>
                    <option value="shedCovering"
                            <%if (type != null && type.equals("shedCovering")) {%> selected <%}%>
                            >Vægbeklædning til skur</option>
                </select>
                <br><br>
                <textarea id="optionArea" name="options" cols="50" rows="<%=(options.size() + 4)%>" style="resize: none;"><%
                    for (String option : options) {
                        out.print(option + "\n");
                    }
                    %></textarea>

                <br><br>
                <button class="btn btn-primary btn-lg" type="submit" name="save" value="save" 
                        <%if (type == null) {%>disabled<%}%>
                        >Gem ændringer</button>
            </form>
            <br>
            <button class="btn btn-primary" onclick="window.location.href = '/FogProject/c/ListRequests';">Tilbage</button>
            <br>
            <%
                String errorK = (String) request.getAttribute("errormessage");
                String errormessageK = "";

                if (errorK == null) {
                    errormessageK = "";
                } else if (errorK.equals("NumberFormat")) {
                    errormessageK = "<p style=\"color:red\">Den valgte type attribut kan kun være tal.</p>";
                }
                out.println(errormessageK);
            %>
        </div>
    </body>
</html>
