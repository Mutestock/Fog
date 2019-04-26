
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;
import java.util.LinkedList;


public class PresentationToLogicImpl implements PresentationToLogic {
    
    private static final LogicToData LOGIC_TO_DATA = new LogicToDataImpl();

    @Override
    public void sendRequest(Request request) throws DataAccessException {
        LOGIC_TO_DATA.sendRequest(request);
    }

    @Override
    public LinkedList<Request> getRequests(String filter) throws DataAccessException {
        return LOGIC_TO_DATA.getRequests(filter);
    }

    @Override
    public Request getRequest(int id) throws DataAccessException {
        return LOGIC_TO_DATA.getRequest(id);
    }

}
