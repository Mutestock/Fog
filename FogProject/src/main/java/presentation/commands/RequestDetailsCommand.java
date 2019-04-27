package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;
import java.io.IOException;
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

    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("r_id"));
            Request r = PRES_TO_LOGIC.getRequest(id);

            request.setAttribute("request", r);

            request.getRequestDispatcher("/WEB-INF/RequestDetails.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        }
    }

}
