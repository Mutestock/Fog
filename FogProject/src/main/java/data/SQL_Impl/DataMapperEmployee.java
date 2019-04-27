package data.SQL_Impl;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import data.DataMapperEmployeeInterface;
import data.customExceptions.DataAccessException;
import data.customExceptions.SQLConnectionException;
import data.help_classes.Carport;
import data.help_classes.Customer;
import data.help_classes.Request;
import data.help_classes.Roof;
import data.help_classes.Shed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataMapperEmployee implements DataMapperEmployeeInterface {

    // reads ALL requests right now -- shall be edited and split into the different request readers in another user story, probably
    @Override
    public LinkedList<Request> readRequestsIncomplete() throws DataAccessException {
        LinkedList<Request> requests = new LinkedList<>();
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select Request_id, Request.`Date`, Request.Comments, "
                    + "Customer.*, "
                    + "Carport.Carport_id, Carport.`Length` as Carport_length, Carport.`Width` as Carport_width, "
                    + "Roof.Roof_id, Roof.`Type` as Roof_type, Roof.Slope as Roof_slope, "
                    + "Shed.Shed_id, Shed.`Length` as Shed_length, Shed.`Width` as Shed_width, Shed.Cover as Shed_cover "
                    + "from Request "
                    + "left join Customer on Request.Customer_id = Customer.Customer_id "
                    + "left join Carport on Request.Carport_id = Carport.Carport_id "
                    + "left join Roof on Carport.Roof_id = Roof.Roof_id "
                    + "left join Shed on Carport.Shed_id = Shed.Shed_id;";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                Request myReq = getRequestFromResultSet(rs);
                requests.add(myReq);
            }
            preparedStmt.close();
            return requests;
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public LinkedList<Request> readRequestsComplete() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Request> readRequestsUnread() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Request> readAllRequests() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Request readRequest(int id) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select Request_id, Request.`Date`, Request.Comments, "
                    + "Customer.*, "
                    + "Carport.Carport_id, Carport.`Length` as Carport_length, Carport.`Width` as Carport_width, "
                    + "Roof.Roof_id, Roof.`Type` as Roof_type, Roof.Slope as Roof_slope, "
                    + "Shed.Shed_id, Shed.`Length` as Shed_length, Shed.`Width` as Shed_width, Shed.Cover as Shed_cover "
                    + "from Request "
                    + "left join Customer on Request.Customer_id = Customer.Customer_id "
                    + "left join Carport on Request.Carport_id = Carport.Carport_id "
                    + "left join Roof on Carport.Roof_id = Roof.Roof_id "
                    + "left join Shed on Carport.Shed_id = Shed.Shed_id "
                    + "where Request_id = ?;";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();

            Request request = null;
            if (rs.next()) {
                request = getRequestFromResultSet(rs);
            }

            preparedStmt.close();
            return request;
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

//    @Override
//    public Customer readCustomer(int id) throws DataAccessException {
//        Customer customer = null;
//        try {
//            PreparedStatement preparedStmt;
//            Connection c = DBConnector.getConnection();
//            String query
//                    = "select * from `Customer` "
//                    + "where `Customer_id` = ?;";
//            preparedStmt = c.prepareStatement(query);
//            preparedStmt.setInt(1, customer.getId());
//            ResultSet rs = preparedStmt.executeQuery();
//            while (rs.next()) {
//                String firstName = rs.getString("FirstName");
//                String lastName = rs.getString("LastName");
//                String address = rs.getString("Address");
//                String zipcode = rs.getString("Zipcode");
//                String city = rs.getString("City");
//                String phone = rs.getString("Phone");
//                String email = rs.getString("Email");
//                customer = new Customer(id, firstName, lastName, address, zipcode, city, phone, email);
//            }
//            preparedStmt.close();
//        } catch (SQLConnectionException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return customer;
//    }

    private Request getRequestFromResultSet(ResultSet rs) throws SQLException {
        Shed shed = null;
        int shedID = rs.getInt("Shed_id");
        int shedLength = rs.getInt("Shed_length");
        int shedWidth = rs.getInt("Shed_width");
        String shedCover = rs.getString("Shed_cover");
        if (shedCover != null) {
            shed = new Shed(shedID, shedLength, shedWidth, shedCover);
        }

        int roofID = rs.getInt("Roof_id");
        String roofType = rs.getString("Roof_type");
        int roofSlope = rs.getInt("Roof_slope");
        Roof roof = new Roof(roofID, roofType, roofSlope);

        int carportID = rs.getInt("Carport_id");
        int carportLength = rs.getInt("Carport_length");
        int carportWidth = rs.getInt("Carport_width");
        Carport carport = new Carport(carportID, carportLength, carportWidth, roof, shed);

        int customerID = rs.getInt("Customer_id");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String address = rs.getString("Address");
        String zipcode = Integer.toString(rs.getInt("Zipcode"));
        String city = rs.getString("City");
        String phone = Integer.toString(rs.getInt("Phone"));
        String email = rs.getString("Email");
        Customer customer = new Customer(customerID, firstName, lastName, address, zipcode, city, phone, email);

        int requestID = rs.getInt("Request_id");
        LocalDateTime requestSent = rs.getObject("Date", LocalDateTime.class);
        String comments = rs.getString("Comments");
        Request myReq = new Request(requestID, requestSent, comments, carport, customer);

        return myReq;
    }

}
