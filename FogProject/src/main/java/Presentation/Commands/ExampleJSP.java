/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henning
 */
public class ExampleJSP extends Command {

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
        System.out.println("Entry at ExampleJSPCommand");
        System.out.println("Entry at ExampleJSPCommand");
        System.out.println("Entry at ExampleJSPCommand");
        loadJSP(request, response);    
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/exampleJSP.jsp").forward(request, response);
    }

}