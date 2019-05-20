package presentation.commands;

import data.customExceptions.DataAccessException;
import data.customExceptions.WrongCredentialsException;
import data.help_classes.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.PresentationToLogic;
import logic.PresentationToLogicImpl;
import presentation.Command;

/**
 *
 * @author Lukas Bjørnvad
 */
public class LoginCheckCommand extends Command {

    /**
     * Command for logging in as adminstrator. Checks whether the user is
     * inserting the correct information in the username and password fields.
     * Throws and exception and informs the user whenever this happens. On a
     * successful login attempt, the user is stored on the session and the
     * customer can proceed to the next admin pages.
     * 
     * Uses the FrontController.
     *
     * @param request The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @param response The servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty. 
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();
            if (username != null && password != null) {
                User user = PRES_TO_LOGIC.getUser(username);

                if (user == null || !user.getPassword().equals(password)) {
                    throw new WrongCredentialsException("Wrong credentials");
                } else {
                    HttpSession session = request.getSession();
                    session.removeAttribute("user");
                    session.setAttribute("user", user);
                    response.sendRedirect("ListRequests");
                }
            } else {
                loadJSP(request, response);
            }
        } catch (WrongCredentialsException ex) {
            request.setAttribute("errormessage", "WrongCredentials");
            loadJSP(request, response);
        } catch (DataAccessException e) {
            e.printStackTrace();
            request.getRequestDispatcher("Crash").forward(request, response);
        }
    }
    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);
    }
}
