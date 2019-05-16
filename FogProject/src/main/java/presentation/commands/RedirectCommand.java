
package presentation.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class RedirectCommand extends Command {

    private final String path;
    
    /** Command for redirecting to .jsp sites, which do not need a separate command. Takes a path as a parameter
     * which then uses a dispatcher to send to the correspondent .jsp site.
     * Used by the FrontController.
     *
     * @param path The path which the command should redirect to.
     */
    public RedirectCommand(String path){
        this.path = path;
    }

    /**
     *
     * @param request The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @param response The servlet container creates an HttpServletResponse object and passes it as an argument to the servlet's service methods (doGet, doPost, etc). 
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty. 
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/"+path+".jsp").forward(request, response);
    }

}
