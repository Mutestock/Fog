
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Customer;
import data.help_classes.Request;

/**
 * @author Simon Asholt Norup
 */
public interface PresentationToLogic {
    
    public void sendRequest(Request request, Customer customer) throws DataAccessException;
    
    // methods for getting available widths, lengths, roof types, etc, etc
    
    // method for creating and/or showing SVG file for carport sketch drawing

}
