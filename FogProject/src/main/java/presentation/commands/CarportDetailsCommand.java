/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Henning
 */
public class CarportDetailsCommand extends Command {

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
        loadJSP(request, response);
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/CarportDetails.jsp").forward(request, response);
    }

}
