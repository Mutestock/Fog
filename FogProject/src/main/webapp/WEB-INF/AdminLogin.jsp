<%-- 
    Document   : AdminLogin
    Created on : 08-05-2019, 12:28:16
    Author     : Lukas Bjørnvad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../CSS/main.css">
    </head>
    <jsp:include page="/inclusions/NavBar.jsp" />
    <body>
        <h1>Log ind</h1>
        <form method="POST" action="/FogProject/c/LoginCheck">
            <input name="username" type="text" value="username">
            <input name="password" type="password" value="password">
            <input type="submit" value="Log Ind">
        </form>
                <%
            String errormessage = (String) request.getAttribute("errormessage");
            if (errormessage == null || errormessage.isEmpty()) {
                errormessage = "";
            }
            out.println(errormessage);
        %>
    </body>
</html>
