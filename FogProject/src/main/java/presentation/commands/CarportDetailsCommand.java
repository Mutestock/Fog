
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
public class CarportDetailsCommand extends Command {
    
    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LinkedList<Integer> lengths = new LinkedList<>();
            for (String s : PRES_TO_LOGIC.getAvailableOptions("length")){
                Integer length = Integer.parseInt(s);
                lengths.add(length);
            }
            LinkedList<Integer> widths = new LinkedList<>();
            for (String s : PRES_TO_LOGIC.getAvailableOptions("width")){
                Integer width = Integer.parseInt(s);
                widths.add(width);
            }
            LinkedList<Integer> roofSlopes = new LinkedList<>();
            for (String s : PRES_TO_LOGIC.getAvailableOptions("roofSlope")){
                Integer roofSlope = Integer.parseInt(s);
                roofSlopes.add(roofSlope);
            }
            LinkedList<Integer> shedLengths = new LinkedList<>();
            for (String s : PRES_TO_LOGIC.getAvailableOptions("shedLength")){
                Integer shedLength = Integer.parseInt(s);
                shedLengths.add(shedLength);
            }
            LinkedList<Integer> shedWidths = new LinkedList<>();
            for (String s : PRES_TO_LOGIC.getAvailableOptions("shedWidth")){
                Integer shedWidth = Integer.parseInt(s);
                shedWidths.add(shedWidth);
            }
            
            request.setAttribute("lengths", lengths);
            request.setAttribute("widths", widths);
            request.setAttribute("roofsFlat", PRES_TO_LOGIC.getAvailableOptions("roofFlat"));
            request.setAttribute("roofsRaised", PRES_TO_LOGIC.getAvailableOptions("roofRaised"));
            request.setAttribute("roofSlopes", roofSlopes);
            request.setAttribute("shedLengths", shedLengths);
            request.setAttribute("shedWidths", shedWidths);
            request.setAttribute("shedCoverings", PRES_TO_LOGIC.getAvailableOptions("shedCovering"));
            
            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        } catch (DataAccessException ex){
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("Crash").forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("errormessage", "NumberFormat");
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }

}
