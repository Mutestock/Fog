package logic;

import data.customExceptions.DataAccessException;
import data.SQL_Impl.DataMapperCustomer;
import data.DataMapperCustomerInterface;
import data.DataMapperEmployeeInterface;
import data.DataMapperUserInterface;
import data.SQL_Impl.DataMapperEmployee;
import data.SQL_Impl.DataMapperUser;
import data.help_classes.Offer;
import data.help_classes.Request;
import data.help_classes.User;
import java.util.LinkedList;

public class LogicToDataImpl implements LogicToData {

    DataMapperCustomerInterface customer_dao;
    DataMapperEmployeeInterface employee_dao;
    DataMapperUserInterface user_dao;

    public LogicToDataImpl() throws DataAccessException {

        customer_dao = new DataMapperCustomer();
        employee_dao = new DataMapperEmployee();
        user_dao = new DataMapperUser();
    }

    @Override
    public void saveRequest(Request request) throws DataAccessException {
        customer_dao.createRequest(request);
    }

    @Override
    public LinkedList<Request> getRequests(String filter) throws DataAccessException {
        switch (filter) {
//            case "unread":
//                return employee_dao.readRequestsUnread();
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

    @Override
    public User getUser(String username) throws DataAccessException {
        return user_dao.getUser(username);
    }

    @Override
    public LinkedList<String> getAvailableOptions(String type) throws DataAccessException {
        return customer_dao.readAvailableOptions(type);
    }

    @Override
    public void setAvailableOptions(LinkedList<String> options, String type) throws DataAccessException {
        employee_dao.updateAvailableOptions(options, type);
    }

}
