package presentation.commands;

import data.customExceptions.DataAccessException;
import data.customExceptions.EmptySessionException;
import data.customExceptions.NoRequestOnSessionException;
import data.help_classes.Offer;
import data.help_classes.PartsList;
import data.help_classes.Request;
import data.help_classes.User;
import java.io.IOException;
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
public class RequestDetailsCommand extends Command {

    private static final Logger logger = LoggerSetup.logSetup();

    /**
     * Command for showing the details of request which the customer ordered.
     * Generates all SVG images. If there is no user on session, it will throw
     * the user back to the adminpage with an error message. Uses a request
     * parameter to display the selected information. Invalidates the session
     * and reassigns the user on the session, so that the user won't have log in
     * again. Uses the FrontController.
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
            final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();
            User user = (User) request.getSession().getAttribute("user");

            if (request.getSession().getAttribute("user") == null) {
                throw new EmptySessionException("Attempt at admin access in requestDetails without admin on session");
            }

            String idParam = request.getParameter("r_id");
            Request r;
            if (idParam == null) {
                r = (Request) request.getSession().getAttribute("request");
                if (r == null) {
                    throw new NoRequestOnSessionException();
                }
            } else {
                request.getSession().invalidate();
                request.getSession().setAttribute("user", user);
                int id = Integer.parseInt(idParam);
                r = PRES_TO_LOGIC.getRequest(id);
                request.getSession().setAttribute("request", r);
            }

            String SVGdrawingAbove = PRES_TO_LOGIC.getSVGDrawing(r.getCarport(), "above");
            String SVGdrawingSide = PRES_TO_LOGIC.getSVGDrawing(r.getCarport(), "side");
            request.setAttribute("SVGabove", SVGdrawingAbove);
            request.setAttribute("SVGside", SVGdrawingSide);

            PartsList partsList = (PartsList) request.getSession().getAttribute("partsList");
            Offer offer = (Offer) request.getSession().getAttribute("offer");
            if (partsList == null) {
                partsList = PRES_TO_LOGIC.getPartsList(r.getCarport());
                request.getSession().setAttribute("partsList", partsList);
            }
            if (offer == null) {
                offer = PRES_TO_LOGIC.getOffer(partsList, r);
                request.getSession().setAttribute("offer", offer);
            }

            request.getRequestDispatcher("/WEB-INF/RequestDetails.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (EmptySessionException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "EmptySession");
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.getRequestDispatcher("EmpLogin").forward(request, response);
        } catch (NoRequestOnSessionException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.toString(), ex);
            request.setAttribute("errormessage", "RequestNull");
            request.getRequestDispatcher("ListRequests").forward(request, response);
        }
    }
}
