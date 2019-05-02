package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.Carport;
import data.help_classes.Offer;
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
import logic.partslist.RoofCalc;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class RequestDetailsCommand extends Command {

    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("r_id");
            Request r;
            if (idParam == null) {
                r = (Request) request.getSession().getAttribute("request");
            } else {
                request.getSession().invalidate();
                int id = Integer.parseInt(idParam);
                r = PRES_TO_LOGIC.getRequest(id);
                request.getSession().setAttribute("request", r);
            }

            PartsList partsList = (PartsList) request.getSession().getAttribute("partsList");
            Offer offer = (Offer) request.getSession().getAttribute("offer");
            if (partsList == null) {
//                partsList = PRES_TO_LOGIC.getPartsList(r.getCarport());
                partsList = getTestList(r.getCarport());
                request.getSession().setAttribute("partsList", partsList);
            }
            if (offer == null) {
                offer = PRES_TO_LOGIC.getOffer(partsList, r);
                request.getSession().setAttribute("offer", offer);
            }

            request.getRequestDispatcher("/WEB-INF/RequestDetails.jsp").forward(request, response);
        } catch (DataAccessException ex) {
            ex.getCause().printStackTrace();
            request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
        }
    }

    private PartsList getTestList(Carport carport) {
        LinkedList<Part> woodPackage = new LinkedList<>();
        woodPackage.add(new Part("Eldergleam", 211, 2, "Cut it down!", 100.0));
        woodPackage.add(new Part("Gleamderel", 211, 2, "No, cut this down!", 200.0));
        LinkedList<Part> roofPackage = RoofCalc.calculateParts(carport);
        LinkedList<Part> fittingsAndScrews = new LinkedList<>();
        fittingsAndScrews.add(new Part("Little Black Eyes", -1, 2, "Put em in your mouthhole, mr.", 5.0));
        PartsList partsList = new PartsList(woodPackage, roofPackage, fittingsAndScrews);
        return partsList;
    }

}
