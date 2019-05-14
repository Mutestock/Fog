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

    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            LinkedList<Request> requests = PRES_TO_LOGIC.getRequests("all");
            request.setAttribute("requests", requests);

            
            System.out.println("User listRequests: " + (request.getSession().getAttribute("user")));
            System.out.println("User listRequests: " + (request.getSession().getAttribute("user")));
            System.out.println("User listRequests: " + (request.getSession().getAttribute("user")));
            
            if (request.getSession().getAttribute("user") == null) {
                throw new EmptySessionException("Attempt at admin access in listRequests without admin on session");
            }

            request.getRequestDispatcher("/WEB-INF/ListRequests.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        } catch (EmptySessionException ex) {
            ex.printStackTrace();
            request.setAttribute("errormessage", "EmptySession");
            request.getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);
        }
    }
}
