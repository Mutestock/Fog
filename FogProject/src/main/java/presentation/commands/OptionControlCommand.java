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

    /**
     * Command class used by the adminstrator whenever they want to change the
     * options available for the customer to choose from when ordering a
     * carport. For example adding new length options, more wall material
     * options, etc. Utilises the datamappers through a facade to update the
     * values. It has functionality to do two things. It either changes type of
     * values or saves the values.
     * Uses the FrontController.
     *
     * @param request The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @param response The servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty. 
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PRES_TO_LOGIC = new PresentationToLogicImpl();

            String type = request.getParameter("type");

            if (request.getParameter("save") == null || request.getAttribute("errormessage") != null) {
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
