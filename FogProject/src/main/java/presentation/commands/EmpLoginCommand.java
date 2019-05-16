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
public class EmpLoginCommand extends Command {

    /**
     * Command class for redirecting to the "ListRequests".jsp. Necessary when
     * a user wants to login as an adminstrator. If the username is incorrect, then the user will get an error message and will need to try again.
     * Used by Front Controller.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
