package presentation.commands;

import data.customExceptions.DataAccessException;
import data.customExceptions.InvalidSymbolException;
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

            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int slope = 0;
            if (request.getParameter("slope") != null) {
                slope = Integer.parseInt(request.getParameter("slope"));
            }
            String roof = request.getParameter("roof");
            System.out.println(roof);
            Roof nRoof = new Roof(-1, roof, slope);
            Shed nShed = null;
            if (request.getParameter("shedwidth") != null && request.getParameter("shedlength") != null) {
                int swidth = Integer.parseInt(request.getParameter("shedwidth"));
                int slength = Integer.parseInt(request.getParameter("shedlength"));
                nShed = new Shed(-1,  slength , swidth, request.getParameter("walls"));
            }
            Carport carport = new Carport(-1, length, width, nRoof, nShed);
            
            Request req = new Request(-1, LocalDateTime.now(), "", carport);

            PartsList partsList = pToL.getPartsList(carport);
            Offer estimate = pToL.getOffer(partsList, req);
            request.getSession().setAttribute("estimate", estimate);

            request.getRequestDispatcher("/WEB-INF/ReviewEstimate.jsp").forward(request, response);

        } catch (InvalidSymbolException ex) {
            request.setAttribute("errormessage", "InvalidSymbol");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        } catch (DataAccessException ex) {
            request.setAttribute("errormessage", "DataAccess");
            request.getRequestDispatcher("CarportDetails").forward(request, response);
        }
    }

}
