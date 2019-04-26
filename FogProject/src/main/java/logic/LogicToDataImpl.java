
package logic;

import data.customExceptions.DataAccessException;
import data.SQL_Impl.DataMapperCustomer;
import data.SQL_Impl.DataMapperCustomerInterface;
import data.help_classes.Request;


public class LogicToDataImpl implements LogicToData {

    @Override
    public void sendRequest(Request request) throws DataAccessException {
        DataMapperCustomerInterface dao = new DataMapperCustomer();
        dao.createRequest(request);
    }

}
