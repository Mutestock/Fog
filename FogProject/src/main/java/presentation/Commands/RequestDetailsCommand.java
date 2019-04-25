package presentation.Commands;

import data.help_classes.Customer;
import data.help_classes.Request;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 * @author Simon Asholt Norup
 */
public class RequestDetailsCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int r_id = Integer.parseInt(request.getParameter("r_id"));
        String c_id = request.getParameter("c_id");
        //Request r = getRequest(r_id);
        //Customer c = getCustomer(c_id);

        request.setAttribute("request", r);
        request.setAttribute("customer", c);

        request.getRequestDispatcher("/WEB-INF/ListRequests.jsp").forward(request, response);
    }

}
