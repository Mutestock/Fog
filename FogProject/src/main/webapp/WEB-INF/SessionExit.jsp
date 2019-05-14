<%-- 
    Document   : SessionExit
    Created on : 05-Mar-2019, 20:44:45
    Author     : Henning
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="SessionExit">
    <%     
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("/c/EmpLogin");        
        rd.forward(request, response);  
    %>
</div>
