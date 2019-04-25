package data.SQL_Impl;


import data.CustomExceptions.DataAccessException;
import data.CustomExceptions.SQLConnectionException;
import data.help_classes.Carport;
import data.help_classes.Customer;
import data.help_classes.Request;
import data.help_classes.Roof;
import data.help_classes.Shed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapperCustomer implements DataMapperCustomerInterface{

    @Override
    public void createCustomer(Customer customer) {
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "insert into `Customer` (`FirstName`, `LastName`, `Address`, `Zipcode`,`City`,`Phone`,`Email`) "
                    + "VALUES(?,?,?,?,?,?)" + ";";
            
            preparedStmt = c.prepareStatement(query);
            
            preparedStmt.setString(1, customer.getFirstName());
            preparedStmt.setString(2, customer.getLastName());
            preparedStmt.setString(3, customer.getAddress());
            preparedStmt.setString(4, customer.getZipcode());
            preparedStmt.setString(5, customer.getCity());
            preparedStmt.setString(6, customer.getPhone());
            preparedStmt.setString(7, customer.getEmail());
            preparedStmt.execute();
            
            preparedStmt.close();
            c.close();
            
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void createRequest(Request request, Customer customer) {
        
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "insert into `Request` (`Carport_id`, `Customer_id`, `Date`, `Comments`) "
                    + "VALUES(?,?,?,?)" + ";";
            
            preparedStmt = c.prepareStatement(query);
            
            preparedStmt.setInt(1, request.getCarport().getId());
            preparedStmt.setInt(2, customer.getId());
            preparedStmt.setObject(3, request.getSent(), java.sql.JDBCType.TIMESTAMP);
            preparedStmt.setString(4, request.getComments());
            
            preparedStmt.execute();
            
            preparedStmt.close();
            c.close();
            
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void createCarport(Carport carport) {
        
        DataMapperCustomer daoC = new DataMapperCustomer();
        
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "insert into `Carport` (`Width`, `Length`, `Shed_id`, `Roof_id`) "
                    + "VALUES(?,?,?,?)" + ";";
            
            preparedStmt = c.prepareStatement(query);
            
            preparedStmt.setInt(1, carport.getWidth());
            preparedStmt.setInt(2, carport.getLength());
            preparedStmt.setInt(3, carport.getShed().getId());
            preparedStmt.setInt(4, carport.getRoof().getId());
            preparedStmt.execute();
            
            preparedStmt.close();
            c.close();
            
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @Override
    public void createRoof(Roof roof) {
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "insert into `Roof` (`Type`, `Slope`) "
                    + "VALUES(?,?)" + ";";
            
            preparedStmt = c.prepareStatement(query);
            
            preparedStmt.setString(1, roof.getType());
            preparedStmt.setInt(2, roof.getSlope());
            preparedStmt.execute();
            
            preparedStmt.close();
            c.close();
            
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void createShed(Shed shed) {
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "insert into `Shed` (`Cover`, `Width`,`Length`) "
                    + "VALUES(?,?,?)" + ";";
            
            preparedStmt = c.prepareStatement(query);
            
            preparedStmt.setString(1, shed.getWallCoverings());
            preparedStmt.setInt(2, shed.getWidth());
            preparedStmt.setInt(3, shed.getLength());
            preparedStmt.execute();
            
            preparedStmt.close();
            c.close();
            
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    // =======================================================
    // ==== SHOULD BE CONVERTED TO JUST GETTING EVERYTHING?? ======
    // ==================================================
    
    @Override
    public int readCustomerId(Customer customer) {
        int customer_id = 0;
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "select `Customer_id` from `Customer` "
                    + "where `Email` = " + customer.getEmail() + ";";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            customer_id = rs.getInt("Customer_id");
            preparedStmt.close();
            c.close();
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return customer_id;
    }

    
    @Override
    public int readShedId(Shed shed) {
        int shed_id = 0;
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "select `Shed_id` from `Shed` "
                    + "where `Width` = " + shed.getWidth()
                    + "and `Length` = " + shed.getLength()
                    + "and `Cover` = " + shed.getWallCoverings() + ";";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            shed_id = rs.getInt("Shed_id");
            preparedStmt.close();
            c.close();
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return shed_id;
    }

    
    @Override
    public int readRoofId(Roof roof) {
        int roof_id = 0;
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "select `Roof_id` from `Roof` "
                    + "where `Type` = " + roof.getType()
                    + "and `Slope` = " + roof.getSlope() + ";";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            roof_id = rs.getInt("Roof_id");
            preparedStmt.close();
            c.close();
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return roof_id;
    }
    

    @Override
    public int readCarportId(Carport carport) {
        
        DataMapperCustomer daoC = new DataMapperCustomer();
        
        int carport_id = 0;
        try {
            PreparedStatement preparedStmt;
            DBConnector connector = new DBConnector();
            Connection c = connector.getConnection();
            String query
                    = "select `Carport_id` from `Carport` "
                    + "where `Width` = " + carport.getWidth()
                    + "and `Length` = " + carport.getLength() 
                    + "and `Shed_id = " + daoC.readShedId(carport.getShed())
                    + "and `Roof_id = " + daoC.readRoofId(carport.getRoof())
                    + ";";
            preparedStmt = c.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            carport_id = rs.getInt("Carport_id");
            preparedStmt.close();
            c.close();
        } catch (SQLConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return carport_id;
    }

    
}
