/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import presentation.commands.*;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.commands.ListRequestsCommand;
import presentation.commands.RequestDetailsCommand;

/**
 *
 * @author Emil PC
 */
public abstract class Command {

    private static final HashMap<String, Command> ACTIONS;
    static {
        ACTIONS = new HashMap<>();
        ACTIONS.put("ReviewEstimate", new ReviewEstimateCommand());
        ACTIONS.put("FrontPage", new RedirectCommand("FrontPage"));
        ACTIONS.put("SendOffer", new SendOfferCommand());
        ACTIONS.put("PartsList", new RedirectCommand("PartsList"));
        ACTIONS.put("SendInformation", new SendInformationCommand());
        ACTIONS.put("CarportDetails", new RedirectCommand("CarportDetails"));
        ACTIONS.put("RequestDetails", new RequestDetailsCommand());
        ACTIONS.put("ListRequests", new ListRequestsCommand());
        ACTIONS.put("EmpLogin", new EmpLoginCommand());
        ACTIONS.put("LoginCheck", new LoginCheckCommand());
        ACTIONS.put("SessionExit", new RedirectCommand("SessionExit"));

    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public static Command from(HttpServletRequest request) {
        String path = request.getPathInfo().substring(1); // substrings "/" out of the path
//        String path = request.getParameter("path");

        
        return ACTIONS.getOrDefault(path, new RedirectCommand("PageNotFound"));
    }
}
