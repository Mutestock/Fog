package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Offer;
import data.help_classes.Request;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import presentation.Command;

/**
 *
 * @author Simon Asholt Norup
 */
public class SendOfferCommand extends Command {

    /**
     * Command for sending the offer from the adminstrator to the customers designated email address, from the requestDetails page.
     * Dispatches back to the ListRequest list, whenever the created offer object has been sent.
     * Uses the FrontController.
     * 
     * @param request The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @param response The servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty. 
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();
            double price = Double.parseDouble(request.getParameter("price"));
            double shippingCosts = Double.parseDouble(request.getParameter("shippingCosts"));
            Request r = (Request) request.getSession().getAttribute("request");

            Offer offer = new Offer(-1, LocalDateTime.now(), price, shippingCosts, r);
            PRES_TO_LOGIC.sendOffer(offer);

            request.getRequestDispatcher("/c/ListRequests").forward(request, response);
        } catch (DataAccessException ex) {
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }

}
