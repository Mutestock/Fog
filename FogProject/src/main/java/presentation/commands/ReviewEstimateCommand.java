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
                nShed = new Shed(-1, swidth, slength, request.getParameter("walls"));
            }
//            System.out.println(nShed.getLength());
            Carport carport = new Carport(-1, length, width, nRoof, nShed);
            System.out.println("We're in review estimate btw");
            System.out.println("We're in review estimate btw");
            System.out.println("We're in review estimate btw");
            System.out.println("We're in review estimate btw");
            System.out.println("We're in review estimate btw");
            
            System.out.println("Carport: " + carport.getId() + carport.getLength() + carport.getWidth() + carport.getRoof() + carport.getShed());
            System.out.println("Carport: " + carport.getId() + carport.getLength() + carport.getWidth() + carport.getRoof() + carport.getShed());
            System.out.println("Carport: " + carport.getId() + carport.getLength() + carport.getWidth() + carport.getRoof() + carport.getShed());
            System.out.println("Carport: " + carport.getId() + carport.getLength() + carport.getWidth() + carport.getRoof() + carport.getShed());
            System.out.println("Carport: " + carport.getId() + carport.getLength() + carport.getWidth() + carport.getRoof() + carport.getShed());
            
            if(carport == null)
            {
                System.out.println("I am null for some reason");
                System.out.println("I am null for some reason");
                System.out.println("I am null for some reason");
                System.out.println("I am null for some reason");
            }

            String comments = request.getParameter("comments");
            if (comments == null)
            {
                comments = "";
            }
            System.out.println(comments);
            Request req = new Request(-1, LocalDateTime.now(), comments, carport);

            PartsList partsList = pToL.getPartsList(carport);
            Offer estimate = pToL.getOffer(partsList, req);
            System.out.println("Price: " + estimate.getPrice());
            System.out.println("Price: " + estimate.getPrice());
            System.out.println("Price: " + estimate.getPrice());
            System.out.println("Price: " + estimate.getPrice());
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

}
