package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.io.IOException;
import java.time.LocalDateTime;
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
public class ReviewEstimateCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PresentationToLogic pToL = new PresentationToLogicImpl();
        try {
            String fname = request.getParameter("firstname");
            String lname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zipcode");
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String comments = request.getParameter("comments");
            Customer cust = new Customer(-1, fname, lname, address, zipcode, city, phone, email);
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int slope = 0;
            if (request.getParameter("slope") != null) {
                slope = Integer.parseInt(request.getParameter("slope"));
            }
            String roof = request.getParameter("roof");
            Roof nRoof = new Roof(-1, roof, slope);
            Shed nShed = null;
            if (request.getParameter("shedwidth") != null && request.getParameter("shedlength") != null) {
                int swidth = Integer.parseInt(request.getParameter("shedwidth"));
                int slength = Integer.parseInt(request.getParameter("shedlength"));
                nShed = new Shed(-1, swidth, slength, request.getParameter("walls"));
            }
            Carport carport = new Carport(-1, length, width, nRoof, nShed);

            Request req = new Request(-1, LocalDateTime.now(), comments, carport, cust);

//            PartsList partsList = pToL.getPartsList(carport);
            PartsList partsList = getTestList(carport);
            Offer estimate = pToL.getOffer(partsList, req);
            request.getSession().setAttribute("estimate", estimate);

            request.getRequestDispatcher("/WEB-INF/ReviewEstimate.jsp").forward(request, response);

        } catch (NumberFormatException x) {
            x.printStackTrace();
            request.getSession().setAttribute("portError", "notnull");
            response.sendRedirect("CarportDetails");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("custInf", "notnull");
            response.sendRedirect("CarportDetails");
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
    }

    private PartsList getTestList(Carport carport) {
        LinkedList<Part> woodPackage = new LinkedList<>();
        woodPackage.add(new Part("Eldergleam", 211, 2, "Cut it down!", 100.0));
        woodPackage.add(new Part("Gleamderel", 211, 2, "No, cut this down!", 200.0));
        LinkedList<Part> roofPackage = RoofCalc.calculateParts(carport);
        LinkedList<Part> fittingsAndScrews = new LinkedList<>();
        fittingsAndScrews.add(new Part("Holy Symbols", 2, "Hear em angels singin'.", 5.0));
        PartsList partsList = new PartsList(woodPackage, roofPackage, fittingsAndScrews);
        return partsList;
    }

}
