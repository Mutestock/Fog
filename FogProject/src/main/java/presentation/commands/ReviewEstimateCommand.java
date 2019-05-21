package presentation.commands;

import data.customExceptions.DataAccessException;
import data.customExceptions.InvalidInputException;
import data.customExceptions.InvalidSymbolException;
import data.help_classes.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.LoggerSetup;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class ReviewEstimateCommand extends Command {

    private static final Logger logger = LoggerSetup.logSetup();

    /**
     * Command for creating a carport for the customer. Shows the dimensions and
     * attributes of the carport for the customer to choose from. Class limits
     * how the customer can choose from the different attributes, depending what
     * the customer currently looks at. e.g. customer can't pick the "choose a
     * carport" variable itself. Throws an error and informs the customer,
     * whenever this happens. Whenever the customer is done estimating the
     * carport attributes, the customer can then procede to review and send
     * requests in the next .jsp: ReviewEstimate, which it dispatches to. Uses
     * the FrontController.
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
    @
            Override

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paramRoof = request.getParameter("roof");
            String isRaised = request.getParameter("isRaised");
            String hasShed = request.getParameter("hasShed");

            final PresentationToLogic pToL = new PresentationToLogicImpl();
            if (request.getParameter("width") == null
                    || request.getParameter("length") == null
                    || paramRoof == null
                    || "on".equals(isRaised) && request.getParameter("slope") == null
                    || "on".equals(hasShed) && request.getParameter("shedlength") == null
                    || "on".equals(hasShed) && request.getParameter("shedwidth") == null
                    || "on".equals(hasShed) && request.getParameter("walls") == null) {

                throw new InvalidInputException("Missing Values in ReviewEstimate");
            }
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
                nShed = new Shed(-1, slength, swidth, request.getParameter("walls"));
            }
            Carport carport = new Carport(-1, length, width, nRoof, nShed);

            Request req = new Request(-1, LocalDateTime.now(), "", carport);

            String SVGdrawingAbove = pToL.getSVGDrawing(carport, "above");
            String SVGdrawingSide = pToL.getSVGDrawing(carport, "side");
            request.setAttribute("SVGabove", SVGdrawingAbove);
            request.setAttribute("SVGside", SVGdrawingSide);

            PartsList partsList = pToL.getPartsList(carport);
            Offer estimate = pToL.getOffer(partsList, req);
            request.getSession().setAttribute("estimate", estimate);
            request.getRequestDispatcher("/WEB-INF/ReviewEstimate.jsp").forward(request, response);

        } catch (InvalidSymbolException ex) {
            request.setAttribute("errormessage", "InvalidSymbol");
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        } catch (DataAccessException ex) {
            request.setAttribute("errormessage", "DataAccess");
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "InvalidInput");
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        }
    }
}
