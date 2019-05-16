<%-- 
    Document   : Crash
    Created on : 13-05-2019, 14:20:09
    Author     : Simon Asholt Norup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Windows-1252">
        <title>Fejlkode 666</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../CSS/main.css">
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <div class="mainbody">
            <h1>Fejlkode 666!</h1>
            <p>
                <%
                    String errormessage = (String) request.getAttribute("errormessage");
                    if (errormessage == null) {
                        errormessage = "Der opstod et alvorligt problem. Kontakt venligst support.";
                    } else if (errormessage.equals("DataAccess")) {
                        errormessage = "Der opstod et problem i databasen. Kontakt venligst support.";
                    } else {
                        errormessage = "Der opstod et alvorligt problem. Kontakt venligst support.";
                    }
                    out.print(errormessage);
                %>
            </p>
        </div>
    </body>
</html>
