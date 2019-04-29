package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;
import java.io.IOException;
import java.util.LinkedList;
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
            String filter = (String) request.getParameter("filter");
            if (filter == null) {
                filter = "incomplete";
            }
            
            LinkedList<Request> requests = PRES_TO_LOGIC.getRequests(filter);
            request.setAttribute("requests", requests);
            
            request.getRequestDispatcher("/WEB-INF/ListRequests.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        }
    }

}
