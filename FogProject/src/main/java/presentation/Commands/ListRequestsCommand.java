package presentation.Commands;

import data.help_classes.Customer;
import data.help_classes.Request;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class ListRequestsCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = (String) request.getParameter("filter");
        if (filter == null) {
            filter = "uncomplete";
        }
        HashMap<Request, Customer> requests;
        switch (filter) {
            case "uncomplete":

                break;
            case "complete":

                break;
            case "unread":

                break;
            default:
                throw new AssertionError();
        }
        request.setAttribute("requests", requests);

        request.getRequestDispatcher("/WEB-INF/ListRequests.jsp").forward(request, response);
    }

}
