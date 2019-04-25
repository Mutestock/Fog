/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import presentation.commands.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.*;

/**
 *
 * @author Emil PC
 */
public class UnknownCommand extends Command {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/PageNotFound.jsp").forward(request, response);
    }
    
}
