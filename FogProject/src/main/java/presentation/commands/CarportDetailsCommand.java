package presentation.commands;

import data.customExceptions.DataAccessException;
import java.io.IOException;
import java.util.LinkedList;
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
public class CarportDetailsCommand extends Command {

    private static PresentationToLogic PRES_TO_LOGIC;
    private static Logger logger = LoggerSetup.logSetup();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PRES_TO_LOGIC = new PresentationToLogicImpl();

            LinkedList<Integer> lengths = getAvailableIntegers("length");
            LinkedList<Integer> widths = getAvailableIntegers("width");
            LinkedList<Integer> roofSlopes = getAvailableIntegers("roofSlope");
            LinkedList<Integer> shedLengths = getAvailableIntegers("shedLength");
            LinkedList<Integer> shedWidths = getAvailableIntegers("shedWidth");

            request.setAttribute("lengths", lengths);
            request.setAttribute("widths", widths);
            request.setAttribute("roofsFlat", PRES_TO_LOGIC.getAvailableOptions("roofFlat"));
            request.setAttribute("roofsRaised", PRES_TO_LOGIC.getAvailableOptions("roofRaised"));
            request.setAttribute("roofSlopes", roofSlopes);
            request.setAttribute("shedLengths", shedLengths);
            request.setAttribute("shedWidths", shedWidths);
            request.setAttribute("shedCoverings", PRES_TO_LOGIC.getAvailableOptions("shedCovering"));

            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            logger.log(Level.SEVERE, "CarportDetailsCommand: DataAccessException", ex);
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (NumberFormatException ex) {
            logger.log(Level.SEVERE, "CarportDetailsCommand: NumberFormatException", ex);
            request.setAttribute("errormessage", "NumberFormat");
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }

    private LinkedList<Integer> getAvailableIntegers(String type) throws DataAccessException, NumberFormatException {
        LinkedList<Integer> list = new LinkedList<>();
        for (String s : PRES_TO_LOGIC.getAvailableOptions(type)) {
            Integer i = Integer.parseInt(s);
            list.add(i);
        }
        return list;
    }

}
