
package data;


import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.util.LinkedList;



public interface DataMapperEmployeeInterface {
    
    public LinkedList<Request> readRequestsIncomplete() throws DataAccessException;
    public LinkedList<Request> readRequestsComplete() throws DataAccessException;
    public LinkedList<Request> readRequestsUnread() throws DataAccessException;
    public LinkedList<Request> readAllRequests()throws DataAccessException;
    
    public Request readRequest(int id) throws DataAccessException;

    public void createOffer(Offer offer) throws DataAccessException;

    public Offer readOffer(int requestID) throws DataAccessException;
}
