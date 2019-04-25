
package Data.SQL_Impl;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
import java.util.LinkedList;


public class DataMapperEmployee implements DataMapperEmployeeInterface {

    @Override
    public LinkedList<Request> readRequestsIncomplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Request> readRequestsComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Request> readRequestsUnread() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Request> readAllRequests() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Request readRequest(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer readCustomer(int id) {
        Customer customer = null;
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "select * from `Customer` "
                    + "where `Customer_id` = ?;";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, customer.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String address = rs.getString("Address");
                String zipcode = rs.getString("Zipcode");
                String city = rs.getString("City");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                customer = new Customer(id, firstName, lastName, address, zipcode, city, phone, email);
            }
            preparedStmt.close();
            c.close();
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return customer;
    }

    @Override
    public Carport readCarport(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Roof readRoof(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shed readShed(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
