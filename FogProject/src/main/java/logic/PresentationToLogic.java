
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.util.LinkedList;

/**
 * @author Simon Asholt Norup
 */
public interface PresentationToLogic {
    
    public void sendRequest(Request request) throws DataAccessException;
    
    public LinkedList<Request> getRequests(String filter) throws DataAccessException;
    
    public Request getRequest(int id) throws DataAccessException;
    
    public LinkedList<String> getAvailableOptions(String type) throws DataAccessException;
    
    public void setAvailableOptions(LinkedList<String> options, String type) throws DataAccessException;
    
    public String getSVGDrawing(Carport carport, String angle);
    
    public PartsList getPartsList(Carport carport);
    
    public Offer getOffer(PartsList parts, Request request) throws DataAccessException;
    
    public void sendOffer(Offer offer) throws DataAccessException;
    
    public User getUser(String username) throws DataAccessException;

}
