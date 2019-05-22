package presentation.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Lukas Bjørnvad
 */
public class AdminLoginCommand extends Command {

    /**
     * Command class for redirecting to the "ListRequests".jsp. Necessary when
     * a user wants to login as an adminstrator. If the username is incorrect, then the user will get an error message and will need to try again.
     * Used by Front Controller.
     *
     * @param request The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @param response The servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty. 
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("ListRequests");
        } else {
            loadJSP(request, response);
        }
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);
    }
}
