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
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class RequestDetailsCommand extends Command {

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
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (EmptySessionException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "EmptySession");
            request.getRequestDispatcher("EmpLogin").forward(request, response);
        } catch (NoRequestOnSessionException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "RequestNull");
            request.getRequestDispatcher("ListRequests").forward(request, response);
        }
    }
}
