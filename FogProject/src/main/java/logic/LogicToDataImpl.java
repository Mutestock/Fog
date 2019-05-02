package logic;

import data.customExceptions.DataAccessException;
import data.SQL_Impl.DataMapperCustomer;
import data.DataMapperCustomerInterface;
import data.DataMapperEmployeeInterface;
import data.SQL_Impl.DataMapperEmployee;
import data.help_classes.Offer;
import data.help_classes.Request;
import java.util.LinkedList;
import logic.LogicToData;

public class LogicToDataImpl implements LogicToData {

    DataMapperCustomerInterface customer_dao = new DataMapperCustomer();
    DataMapperEmployeeInterface employee_dao = new DataMapperEmployee();

    @Override
    public void saveRequest(Request request) throws DataAccessException {
        customer_dao.createRequest(request);
    }

    @Override
    public LinkedList<Request> getRequests(String filter) throws DataAccessException {
        switch (filter) {
            case "incomplete":
                return employee_dao.readRequestsIncomplete();
            case "complete":
                return employee_dao.readRequestsComplete();
            case "unread":
                return employee_dao.readRequestsUnread();
            default:
                return employee_dao.readAllRequests();
        }
    }

    @Override
    public Request getRequest(int id) throws DataAccessException {
        return employee_dao.readRequest(id);
    }

    @Override
    public void saveOffer(Offer offer) throws DataAccessException {
        employee_dao.createOffer(offer);
    }

    @Override
    public Offer getOffer(int requestID) throws DataAccessException {
        return employee_dao.readOffer(requestID);
    }

}
