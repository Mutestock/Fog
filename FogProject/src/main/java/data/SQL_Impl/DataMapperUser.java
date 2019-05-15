package data.SQL_Impl;

import data.DataMapperUserInterface;
import data.customExceptions.DataAccessException;
import data.help_classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataMapperUser implements DataMapperUserInterface {

    
    /**
     * Inserts user object information to database.
     * @param user object with user information.
     * @throws DataAccessException when access to database fails.
     */
    @Override
    public void addUser(User user) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            String query
                    = "insert into `users` (`username`, `password`) "
                    + "VALUES(?,?);";
            preparedStmt = c.prepareStatement(query);

            preparedStmt.setString(1, user.getUsername());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.execute();
            
            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    
    /**
     * Return user object with user information based on username.
     * @param username object value from the user class.
     * @return user object based on username.
     * @throws DataAccessException when access to database fails.
     */
    @Override
    public User getUser(String username) throws DataAccessException {
        try {
            PreparedStatement preparedStmt;
            Connection c = DBConnector.getConnection();
            User user = null;
            String query
                    = "SELECT * FROM `users` WHERE `username`= ?;";

            preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, username);
            
            ResultSet rs = preparedStmt.executeQuery();
            String password = "";
            username = "";
            while (rs.next()) {
                password = rs.getString("password");
                username = rs.getString("username");
            }
            if (username != null && !username.isEmpty()) {
                user = new User(username, password);
            }
            
            preparedStmt.close();
            return user;
        } catch (SQLException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

}
