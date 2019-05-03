
package presentation.commands;

import data.help_classes.PartsList;
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
public class PartsListCommand extends Command {
    
    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Request r = (Request) request.getSession().getAttribute("request");
            
            PartsList partsList;
            
            partsList = (PartsList) request.getSession().getAttribute("partsList");
            
            request.setAttribute("request", r);
            request.setAttribute("partsList", partsList);
            
            request.getRequestDispatcher("/WEB-INF/PartsList.jsp").forward(request, response);
        }

}
