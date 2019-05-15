package data.SQL_Impl;

import data.DataMapperCustomerInterface;
import data.customExceptions.DataAccessException;
import data.help_classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapperCustomer implements DataMapperCustomerInterface {

    @Override
    public void createRequest(Request request) throws DataAccessException {

        Carport carport = request.getCarport();
        Shed shed = carport.getShed();

        int roofID = createRoof(carport.getRoof());
        int shedID = -1;
        if (shed != null) {
            shedID = createShed(shed);
        }
        int carportID = createCarport(carport, roofID, shedID);
        int customerID = createCustomer(request.getCustomer());
        createRequest(request, carportID, customerID);
    }

    private void createRequest(Request request, int carportID, int customerID) throws DataAccessException {

        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Request` (`Carport_id`, `Customer_id`, `Date`, `Comments`) "
                    + "VALUES(?,?,?,?)" + ";";

            preparedStmt = c.prepareStatement(query);

            preparedStmt.setInt(1, carportID);
            preparedStmt.setInt(2, customerID);
            preparedStmt.setObject(3, request.getSent(), java.sql.JDBCType.TIMESTAMP);
            preparedStmt.setString(4, request.getComments());

            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    private int createCustomer(Customer customer) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Customer` (`FirstName`, `LastName`, `Address`, `Zipcode`,`City`,`Phone`,`Email`) "
                    + "VALUES(?,?,?,?,?,?,?)" + ";";

            preparedStmt = c.prepareStatement(query, new int[]{1}); // return auto generated row in column 1

            preparedStmt.setString(1, customer.getFirstName());
            preparedStmt.setString(2, customer.getLastName());
            preparedStmt.setString(3, customer.getAddress());
            preparedStmt.setString(4, customer.getZipcode());
            preparedStmt.setString(5, customer.getCity());
            preparedStmt.setString(6, customer.getPhone());
            preparedStmt.setString(7, customer.getEmail());
            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            int id;
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                id = readCustomerID(customer);
            }

            preparedStmt.close();
            return id;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    private int createCarport(Carport carport, int roofID, int shedID) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Carport` (`Width`, `Length`, `Shed_id`, `Roof_id`) "
                    + "VALUES(?,?,?,?)" + ";";

            preparedStmt = c.prepareStatement(query, new int[]{1}); // return auto generated row in column 1

            preparedStmt.setInt(1, carport.getWidth());
            preparedStmt.setInt(2, carport.getLength());
            if (shedID == -1) {
                preparedStmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                preparedStmt.setInt(3, shedID);
            }
            preparedStmt.setInt(4, roofID);
            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            preparedStmt.close();
            return id;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    private int createRoof(Roof roof) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Roof` (`Type`, `Slope`) "
                    + "VALUES(?,?);";

            preparedStmt = c.prepareStatement(query, new int[]{1}); // return auto generated row in column 1

            preparedStmt.setString(1, roof.getType());
            preparedStmt.setInt(2, roof.getSlope());
            preparedStmt.executeUpdate();

            ResultSet rs = preparedStmt.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);

            preparedStmt.close();
            return id;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    private int createShed(Shed shed) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `Shed` (`Cover`, `Width`,`Length`) "
                    + "VALUES(?,?,?)" + ";";

            preparedStmt = c.prepareStatement(query, new int[]{1}); // return auto generated row in column 1

            preparedStmt.setString(1, shed.getWallCoverings());
            preparedStmt.setInt(2, shed.getWidth());
            preparedStmt.setInt(3, shed.getLength());
            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            preparedStmt.close();
            return id;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    private int readCustomerID(Customer customer) throws DataAccessException {
        int customerID;
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select `Customer_id` from `Customer` "
                    + "where `Email` = ?;";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, customer.getEmail());
            
            ResultSet rs = preparedStmt.executeQuery();
            customerID = rs.getInt("Customer_id");
            preparedStmt.close();
            return customerID;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }
}
