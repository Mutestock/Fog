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
     * @param request s
     * @param response s
     * @throws ServletException s
     * @throws IOException s
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final PresentationToLogic pToL = new PresentationToLogicImpl();
            Offer estimate = (Offer) request.getSession().getAttribute("estimate");
            String fname = request.getParameter("firstname");
            String lname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zipcode");
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String comments = request.getParameter("comments");
            if (comments == null) {
                comments = "";
            }

            Customer cust = new Customer(-1, fname, lname, address, zipcode, city, phone, email);
            System.out.println("Customer: " + cust);
            Carport carport = estimate.getRequest().getCarport();
            estimate.setRequest(new Request(-1, LocalDateTime.now(), comments, carport, cust));

            pToL.sendRequest(estimate.getRequest());

        } catch (NumberFormatException x) {
            x.printStackTrace();
            request.getSession().setAttribute("portError", "notnull");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
            //response.sendRedirect("CarportDetails");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("custInf", "notnull");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
            //response.sendRedirect("CarportDetails");
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }

        loadJSP(request, response);
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/FogProject/index.jsp");
    }

}
