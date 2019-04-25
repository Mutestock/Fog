
package Logic;

import Data.customExceptions.DataAccessException;
import Data.help_classes.Customer;
import Data.help_classes.Request;

/**
 * @author Simon Asholt Norup
 */
public interface PresentationToLogic {
    
    public void sendRequest(Request request, Customer customer) throws DataAccessException;
    
    // methods for getting available widths, lengths, roof types, etc, etc
    
    // method for creating and/or showing SVG file for carport sketch drawing

}
