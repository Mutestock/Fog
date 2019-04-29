package data;

import data.customExceptions.DataAccessException;
import data.help_classes.*;

/**
 *
 * @author Emil PC
 */
public interface DataMapperCustomerInterface {
    
    public void createRequest(Request request) throws DataAccessException;
    
}
