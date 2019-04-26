
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;

/**
 * @author Simon Asholt Norup
 */
public interface LogicToData {
    
    public void sendRequest(Request request) throws DataAccessException;
    
    // methods for getting available widths, lengths, roof types, etc, etc

}
