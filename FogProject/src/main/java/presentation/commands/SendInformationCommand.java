/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import data.customExceptions.DataAccessException;
import data.help_classes.*;
import presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import java.time.LocalDateTime;

/**
 *
 * @author Henning
 */
public class SendInformationCommand extends Command {

    /**
     * Basic login functionality, checks the username and password. Prints out
     * errormessages if either username or password is wrong.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PresentationToLogic pToL = new PresentationToLogicImpl();
        try {
            Offer estimate = (Offer) request.getSession().getAttribute("estimate");
            Request req = estimate.getRequest();
            pToL.sendRequest(req);

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

        loadJSP(request, response);
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/FogProject/index.html");
    }

}
