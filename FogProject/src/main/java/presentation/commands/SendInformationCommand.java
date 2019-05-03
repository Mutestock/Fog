/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.*;
import presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import java.time.LocalDateTime;

/**
 *
 * @author Henning
 */
public class SendInformationCommand extends Command {

    /**
     * Basic login functionality, checks the username and password. Prints out
     * errormessages if either username or password is wrong.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PresentationToLogic pToL = new PresentationToLogicImpl();
        try {
            String fname = request.getParameter("firstname");
           
            String lname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zipcode");
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String comments = request.getParameter("comments");
            Customer cust = new Customer(-1, fname, lname, address, zipcode, city, phone, email);
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int slope = 0;
            if (request.getParameter("slope") != null) {
                slope = Integer.parseInt(request.getParameter("slope"));
            }
            String roof = request.getParameter("roof");
            Roof nRoof = new Roof(-1, roof, slope);
            Shed nShed = null;
            if (request.getParameter("shedwidth") != null && request.getParameter("shedlength") != null) {
                int swidth = Integer.parseInt(request.getParameter("shedwidth"));
                int slength = Integer.parseInt(request.getParameter("shedlength"));
                nShed = new Shed(-1, swidth, slength, request.getParameter("walls"));
            }
            Carport carport = new Carport(-1, length, width, nRoof, nShed);

            LocalDateTime now = LocalDateTime.now();
            Request req = new Request(-1, now, comments, carport, cust);
            pToL.sendRequest(req);

        } catch (NumberFormatException x) {
            x.printStackTrace();
            request.getSession().setAttribute("portError", "notnull");
            response.sendRedirect("CarportDetails");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("custInf", "notnull");
            response.sendRedirect("CarportDetails");
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }

        loadJSP(request, response);
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/FogProject/index.jsp");
    }

}
