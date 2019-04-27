
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;
import java.util.LinkedList;

/**
 * @author Simon Asholt Norup
 */
public interface LogicToData {
    
    public void sendRequest(Request request) throws DataAccessException;
    
    public LinkedList<Request> getRequests(String filter) throws DataAccessException;
    
    public Request getRequest(int id) throws DataAccessException;
    
    // methods for getting available widths, lengths, roof types, etc, etc

}