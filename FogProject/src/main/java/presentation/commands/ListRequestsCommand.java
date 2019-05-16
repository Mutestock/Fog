package presentation.commands;

import data.customExceptions.DataAccessException;
import data.customExceptions.EmptySessionException;
import data.help_classes.Request;
import java.io.IOException;
import java.util.LinkedList;
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
public class ListRequestsCommand extends Command {

    /**
     * Command class for obtaining information for the ListRequests.jsp site. If
     * there is no user on the session, then the user will be thrown back to the
     * login screen The connected .jsp site then redirects to the details
     * command via a button in a table for each entry. 
     * Used by the FrontController.
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
            LinkedList<Request> requests = PRES_TO_LOGIC.getRequests("all");
            request.setAttribute("requests", requests);

            if (request.getSession().getAttribute("user") == null) {
                throw new EmptySessionException("Attempt at admin access in listRequests without admin on session");
            }

            request.getRequestDispatcher("/WEB-INF/ListRequests.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (EmptySessionException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "EmptySession");
            request.getRequestDispatcher("EmpLogin").forward(request, response);
        }
    }
}
