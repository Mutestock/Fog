package data.SQL_Impl;

import data.DataMapperEmployeeInterface;
import data.customExceptions.DataAccessException;
import data.help_classes.Carport;
import data.help_classes.Customer;
import data.help_classes.Offer;
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

    @Override
    public LinkedList<Request> readAllRequests() throws DataAccessException {
        LinkedList<Request> requests = new LinkedList<>();
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select Request.Request_id, Request.`Date`, Request.Comments, "
                    + "Offer.Offer_id, Customer.*, "
                    + "Carport.Carport_id, Carport.`Length` as Carport_length, Carport.`Width` as Carport_width, "
                    + "Roof.Roof_id, Roof.`Type` as Roof_type, Roof.Slope as Roof_slope, "
                    + "Shed.Shed_id, Shed.`Length` as Shed_length, Shed.`Width` as Shed_width, Shed.Cover as Shed_cover "
                    + "from Request "
                    + "left join Customer on Request.Customer_id = Customer.Customer_id "
                    + "left join Carport on Request.Carport_id = Carport.Carport_id "
                    + "left join Roof on Carport.Roof_id = Roof.Roof_id "
                    + "left join Shed on Carport.Shed_id = Shed.Shed_id "
                    + "left join Offer on Request.Request_id = Offer.Request_id "
                    + "ORDER BY Request.Request_id DESC;";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                Request myReq = getRequestFromResultSet(rs);
                requests.add(myReq);
            }
            preparedStmt.close();
            return requests;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    @Override
    public Request readRequest(int id) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select Request.Request_id, Request.`Date`, Request.Comments, "
                    + "Offer.Offer_id, Customer.*, "
                    + "Carport.Carport_id, Carport.`Length` as Carport_length, Carport.`Width` as Carport_width, "
                    + "Roof.Roof_id, Roof.`Type` as Roof_type, Roof.Slope as Roof_slope, "
                    + "Shed.Shed_id, Shed.`Length` as Shed_length, Shed.`Width` as Shed_width, Shed.Cover as Shed_cover "
                    + "from Request "
                    + "left join Customer on Request.Customer_id = Customer.Customer_id "
                    + "left join Carport on Request.Carport_id = Carport.Carport_id "
                    + "left join Roof on Carport.Roof_id = Roof.Roof_id "
                    + "left join Shed on Carport.Shed_id = Shed.Shed_id "
                    + "left join Offer on Request.Request_id = Offer.Request_id "
                    + "where Request.Request_id = ?;";
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
            throw new DataAccessException(ex.getMessage());
        }
    }

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
        int offerID = rs.getInt("Offer_id");
        boolean hasReceivedOffer = offerID != 0;
        Request myReq = new Request(requestID, requestSent, comments, carport, customer, hasReceivedOffer);

        return myReq;
    }

    @Override
    public void createOffer(Offer offer) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Offer` (`Price`, `Shipping`, `Date`, `Request_id`) "
                    + "VALUES(?,?,?,?)" + ";";

            preparedStmt = c.prepareStatement(query);

            preparedStmt.setDouble(1, offer.getPrice());
            preparedStmt.setDouble(2, offer.getShippingCosts());
            preparedStmt.setObject(3, offer.getSent(), java.sql.JDBCType.TIMESTAMP);
            preparedStmt.setInt(4, offer.getRequest().getId());

            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    @Override
    public Offer readOffer(int requestID) throws DataAccessException {
        Request request = readRequest(requestID);

        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select Offer_id, Price, Shipping, Offer.`Date` "
                    + "from Offer left join Request on Offer.Request_id = Request.Request_id "
                    + "where Request.Request_id = ?;";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, requestID);
            ResultSet rs = preparedStmt.executeQuery();

            Offer offer = null;
            if (rs.next()) {
                int offerID = rs.getInt("Offer_id");
                double price = rs.getDouble("Price");
                double shippingCosts = rs.getDouble("Shipping");
                LocalDateTime offerSent = rs.getObject("Date", LocalDateTime.class);
                offer = new Offer(offerID, offerSent, price, shippingCosts, request);
            }

            preparedStmt.close();
            return offer;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    @Override
    public void updateAvailableOptions(LinkedList<String> options, String type) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();

            String query
                    = "DELETE FROM AvailableOptions "
                    + "WHERE `Type` = ?";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, type);
            preparedStmt.execute();
            preparedStmt.close();

            query
                    = "INSERT INTO AvailableOptions (`Type`, `Value`) "
                    + "VALUES (?, ?)";
            preparedStmt = c.prepareStatement(query);

            for (String option : options) {
                preparedStmt.setString(1, option);
                preparedStmt.setString(2, type);
                preparedStmt.addBatch();
            }
            preparedStmt.executeBatch();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

}
