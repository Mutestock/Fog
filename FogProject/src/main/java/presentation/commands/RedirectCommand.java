
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
    
    public RedirectCommand(String path){
        this.path = path;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/"+path+".jsp").forward(request, response);
    }

}
