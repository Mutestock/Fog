package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Offer;
import data.help_classes.Request;
import java.io.IOException;
import java.time.LocalDateTime;
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
public class UpdateOptionsCommand extends Command {

    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String optionText = request.getParameter("options");
            String type = request.getParameter("type");

            LinkedList<String> options = new LinkedList<>();
            String[] lines = optionText.split("\\r?\\n");
            for (String line : lines) {
                if (!line.isEmpty()) {
                    options.add(line);
                }
            }

            PRES_TO_LOGIC.setAvailableOptions(options, type);

            request.getRequestDispatcher("/c/CarportDetails").forward(request, response);
        } catch (DataAccessException ex) {
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }

}
