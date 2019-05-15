package presentation.commands;

import data.customExceptions.DataAccessException;
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
public class OptionControlCommand extends Command {

    private static PresentationToLogic PRES_TO_LOGIC;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PRES_TO_LOGIC = new PresentationToLogicImpl();

            String type = request.getParameter("type");

            if (request.getParameter("save") == null) {
                updateToOptionType(type, request);
            } else {
                saveOptions(type, request);
            }

            request.getRequestDispatcher("/WEB-INF/OptionControl.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("errormessage", "NumberFormat");
            request.getRequestDispatcher("OptionControl").forward(request, response);
        }
    }

    private void updateToOptionType(String type, HttpServletRequest request) throws DataAccessException {
        LinkedList<String> options;
        if (type == null) {
            options = new LinkedList<>();
        } else {
            options = PRES_TO_LOGIC.getAvailableOptions(type);
        }
        request.setAttribute("type", type);
        request.setAttribute("options", options);
    }

    private void saveOptions(String type, HttpServletRequest request) throws DataAccessException {
        String optionText = request.getParameter("options");
        LinkedList<String> options = new LinkedList<>();

        boolean isIntegerType = (!type.equals("roofFlat") && !type.equals("roofRaised") && !type.equals("shedCovering"));

        String[] lines = optionText.split("\\r?\\n");
        for (String line : lines) {
            if (!line.isEmpty()) {
                if (isIntegerType) {
                    Integer.parseInt(line);
                }
                options.add(line);
            }
        }
        request.setAttribute("options", new LinkedList<>());

        PRES_TO_LOGIC.setAvailableOptions(options, type);
    }

}
