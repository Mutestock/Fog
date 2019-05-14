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
    
    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double price = Double.parseDouble(request.getParameter("price"));
            double shippingCosts = Double.parseDouble(request.getParameter("shippingCosts"));
            Request r = (Request) request.getSession().getAttribute("request");
            
            Offer offer = new Offer(-1, LocalDateTime.now(), price, shippingCosts, r);
            PRES_TO_LOGIC.sendOffer(offer);

            request.getRequestDispatcher("/c/ListRequests").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }

}
