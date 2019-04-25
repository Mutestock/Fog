package Data.SQL_Impl;

import data.help_classes.*;
import java.util.LinkedList;



public interface DataMapperEmployeeInterface {
    
    public LinkedList<Request> readRequestsIncomplete();
    public LinkedList<Request> readRequestsComplete();
    public LinkedList<Request> readRequestsUnread();
    public LinkedList<Request> readAllRequests();
    
    public Request readRequest(int id);
    public Customer readCustomer(int id);
    public Carport readCarport(int id);
    public Roof readRoof(int id);
    public Shed readShed(int id);
    
}
