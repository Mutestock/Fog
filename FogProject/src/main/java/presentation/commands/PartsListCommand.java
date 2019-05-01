
package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Carport;
import data.help_classes.Part;
import data.help_classes.PartsList;
import data.help_classes.Request;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import logic.partslist.WoodCalc;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class PartsListCommand extends Command {
    
    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
            Request r = (Request) request.getSession().getAttribute("request");
            
            PartsList partsList;
//          partsList = PRES_TO_LOGIC.getPartsList(r.getCarport());
            partsList = getTestList(r.getCarport());
            
            request.setAttribute("request", r);
            request.setAttribute("partsList", partsList);
            
            request.getRequestDispatcher("/WEB-INF/PartsList.jsp").forward(request, response);
//        } catch (DataAccessException ex) {
//            ex.getCause().printStackTrace();
//            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
//        }
        }
    
    private PartsList getTestList(Carport carport){
        //LinkedList<Part> woodPackage = new LinkedList<>();
        LinkedList<Part> woodPackage = WoodCalc.calculateParts(carport);
        /*woodPackage.add(new Part("Eldergleam", 211, 2, "Cut it down!", 100.0));
        woodPackage.add(new Part("Gleamderel", 211, 2, "No, cut this down!", 200.0));*/
        LinkedList<Part> roofPackage = new LinkedList<>();
        LinkedList<Part> fittingsAndScrews = new LinkedList<>();
        fittingsAndScrews.add(new Part("Little Black Eyes", -1, 2, "Put em in your mouthhole, mr.", 5.0));
        PartsList partsList = new PartsList(woodPackage, roofPackage, fittingsAndScrews);
        return partsList;
    }

}
