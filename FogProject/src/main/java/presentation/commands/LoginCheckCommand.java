package presentation.commands;

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
public class LoginCheckCommand extends Command{
    private static final PresentationToLogic PRES_TO_LOGIC = new PresentationToLogicImpl();  
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            if (username != null && password != null) {
                User user = PRES_TO_LOGIC.getUser(username);
                
                if (user == null) {
                    request.setAttribute("errormessage", "<p style=\"color:red\">User doesn't exist.</p>");
                    loadJSP(request, response);
                } else if (!user.getPassword().equals(password)) {
                    request.setAttribute("errormessage", "<p style=\"color:red\">Incorrect password</p>");
                    loadJSP(request, response);
                    
                } else {
                    HttpSession session = request.getSession();
                    session.removeAttribute("user");
                    session.setAttribute("user", user);
                    response.sendRedirect("ListRequests");
                }
            } else {
                loadJSP(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    private void loadJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/AdminLogin.jsp").forward(request, response);
    }
 
}
