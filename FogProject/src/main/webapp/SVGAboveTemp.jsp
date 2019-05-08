<%-- 
    Document   : SVGAboveTemp
    Created on : May 7, 2019, 11:45:46 AM
    Author     : Simon Asholt Norup
--%>

<%@page import="logic.SVG.SVGDrawerFromSide"%>
<%@page import="data.help_classes.Carport"%>
<%@page import="data.help_classes.Shed"%>
<%@page import="data.help_classes.Roof"%>
<%@page import="logic.SVG.SVGDrawerFromAbove"%>
<%
    SVGDrawerFromAbove SVGdrawer = new SVGDrawerFromAbove();
    SVGDrawerFromSide SVGdrawer2 = new SVGDrawerFromSide();

    Roof roof = new Roof(1, "Plastic", 0);
    Shed shed = new Shed(1, 100, 300, "Plastic");
    Carport carport = new Carport(1, 600, 360, roof, shed);
    String drawing = SVGdrawer.drawCarportFlatRoof(carport);
    String drawing2 = SVGdrawer2.drawCarportFlatRoofSide(carport);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        
        <%=drawing%>
        <%=drawing2%>
    </body>
</html>
