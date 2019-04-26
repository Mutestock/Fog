
package logic;

import data.customExceptions.DataAccessException;
import data.help_classes.Request;


public class PresentationToLogicImpl implements PresentationToLogic {
    
    private static final LogicToData LOGIC_TO_DATA = new LogicToDataImpl();

    @Override
    public void sendRequest(Request request) throws DataAccessException {
        LOGIC_TO_DATA.sendRequest(request);
    }

}
