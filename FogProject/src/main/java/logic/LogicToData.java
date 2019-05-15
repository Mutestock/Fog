
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Offer;
import data.help_classes.Request;
import data.help_classes.User;
import java.util.LinkedList;

/**
 * @author Simon Asholt Norup
 */
public interface LogicToData {
    
    public void saveRequest(Request request) throws DataAccessException;
    
    public LinkedList<Request> getRequests(String filter) throws DataAccessException;
    
    public Request getRequest(int id) throws DataAccessException;
    
    public LinkedList<String> getAvailableOptions(String type) throws DataAccessException;
    
    public void setAvailableOptions(LinkedList<String> options, String type) throws DataAccessException;
    
    public void saveOffer(Offer offer) throws DataAccessException;
    
    public Offer getOffer(int requestID) throws DataAccessException;
    
    public User getUser(String username) throws DataAccessException;

}
