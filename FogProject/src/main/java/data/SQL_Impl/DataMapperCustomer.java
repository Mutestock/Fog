package data.SQL_Impl;

import data.DataMapperCustomerInterface;
import data.customExceptions.DataAccessException;
import data.customExceptions.SQLConnectionException;
import data.help_classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DataMapperCustomer implements DataMapperCustomerInterface {

    private DBConnector dBC;

    public DataMapperCustomer(boolean test) throws DataAccessException {
        try {
            if (test) {
                dBC = new DBConnector(true);
            } else {
                dBC = new DBConnector();
            }
        } catch (SQLConnectionException ex) {
            throw new DataAccessException();
        }
    }

    public DataMapperCustomer() throws DataAccessException {
        this(false);
    }

    /**
     * Inserts request object values to database.
     *
     * @param request object (not http class object) with carport and customer
     * information.
     * @throws DataAccessException when access to database fails.
     */
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

        int customerID = readCustomerID(request.getCustomer());
        if (customerID == -1) {
            customerID = createCustomer(request.getCustomer());
        }

        createRequest(request, carportID, customerID);
    }

    /**
     * Inserts request object values to database.
     *
     * @param request request object (not http class object) with carport and
     * customer information.
     * @param carportID used to save the carport information from the database.
     * @param customerID used to save the customer information from the
     * database.
     * @throws DataAccessException when access to database fails.
     */
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

    /**
     * Inserts customer object values to database, unless one with same email
     * exists, then simply return ID of already existing customer row.
     *
     * @param customer object with customer information.
     * @return the customer id as it has been added to the database.
     * @throws DataAccessException when access to database fails.
     */
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
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            preparedStmt.close();
            return id;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    /**
     * Inserts carport object values to database.
     *
     * @param carport object with carport information.
     * @param roofID used to get roof information from database.
     * @param shedID used to get shed information from database.
     * @return the carport id as it has been added to the database.
     * @throws DataAccessException when access to database fails.
     */
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

    /**
     * Inserts roof object values to database.
     *
     * @param roof object with roof information.
     * @return the roof id as it has been added to the database.
     * @throws DataAccessException when access to database fails.
     */
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

    /**
     * Inserts shed object values to database.
     *
     * @param shed object with roof information.
     * @return the shed id as it has been added to the database.
     * @throws DataAccessException when access to database fails.
     */
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

    /**
     * Gets the ID of a customer from the database based on a customer object.
     *
     * @param customer object with customer information.
     * @return the customer id when the customer is found in the database.
     * @throws DataAccessException when access to database fails.
     */
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
            if (rs.next()) {
                customerID = rs.getInt("Customer_id");
            } else {
                customerID = -1;
            }
            preparedStmt.close();
            return customerID;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    @Override
    public LinkedList<String> readAvailableOptions(String type) throws DataAccessException {
        LinkedList<String> result = new LinkedList<>();
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "select `Value` from `AvailableOptions` "
                    + "where `Type` = ?;";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, type);

            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("Value"));
            }
            preparedStmt.close();
            return result;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }
}
