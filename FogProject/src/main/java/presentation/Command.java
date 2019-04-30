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
import presentation.commands.RequestDetailsCommand;

/**
 *
 * @author Emil PC
 */
public abstract class Command {

    public abstract void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public static Command from(HttpServletRequest request) {
        String path = request.getPathInfo().substring(1); // substrings "/" out of the path
//        String path = request.getParameter("path");

        HashMap<String, Command> actions = new HashMap<String, Command>() {
            {
                put("PartsList", new PartsListCommand());
                put("SendInformation", new SendInformationCommand());
                put("CarportDetails", new CarportDetailsCommand());
                put("RequestDetails", new RequestDetailsCommand());
                put("ListRequests", new ListRequestsCommand());
            }
        };
        return actions.getOrDefault(path, new UnknownCommand());
    }
}
