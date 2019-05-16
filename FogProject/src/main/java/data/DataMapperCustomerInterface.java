package data;

import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.util.LinkedList;

/**
 *
 * @author Emil PC
 */
public interface DataMapperCustomerInterface {
    
    public LinkedList<String> readAvailableOptions(String type) throws DataAccessException;
    
    public void createRequest(Request request) throws DataAccessException;
    
}
