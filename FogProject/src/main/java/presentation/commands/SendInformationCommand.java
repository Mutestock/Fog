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
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.LoggerSetup;

/**
 *
 * @author Henning
 */
public class SendInformationCommand extends Command {

    private static final Logger logger = LoggerSetup.logSetup();

    /**
     * Generates a customer upon acceptance of the transaction from the
     * customers side. This happens after the customer has reviewed the details
     * of the transaction.
     *
     * @param request The servlet container creates an HttpServletRequest object
     * and passes it as an argument to the servlet's service methods (doGet,
     * doPost, etc).
     * @param response The servlet container creates an HttpServletResponse
     * object and passes it as an argument to the servlet's service methods
     * (doGet, doPost, etc).
     * @throws ServletException Defines a general exception a servlet can throw
     * when it encounters difficulty.
     * @throws IOException Signals that an I/O exception of some sort has
     * occurred. This class is the general class of exceptions produced by
     * failed or interrupted I/O operations.
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

            if (Integer.parseInt(zipcode) >= 3700 && Integer.parseInt(zipcode) <= 4000) {
                logger.log(Level.SEVERE, "ReviewEstimateCommand: Illegal Zipcode");
                throw new IllegalArgumentException("Illegal zipcode");
            }

            Customer cust = new Customer(-1, fname, lname, address, zipcode, city, phone, email);
            System.out.println("Customer: " + cust);
            Carport carport = estimate.getRequest().getCarport();
            estimate.setRequest(new Request(-1, LocalDateTime.now(), comments, carport, cust));

            pToL.sendRequest(estimate.getRequest());

        } catch (NumberFormatException x) {
            x.printStackTrace();
            logger.log(Level.SEVERE, x.toString(), x);
            request.setAttribute("errormessage", "InvalidInput");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.setAttribute("errormessage", "InvalidInput");
            request.getRequestDispatcher("CarportDetails").forward(request, response);

        } catch (DataAccessException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        }

        loadJSP(request, response);
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/FogProject/c/ThankYou");
    }

}
