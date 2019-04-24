package Data.SQL_Impl;

import Data.CustomExceptions.SQLConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to connect to any MySQL database that allows remote
 * access. What database to connect to and what SQL user to log in as is
 * determined by the constant variables currently hardcoded. Currently, the
 * DBConnector connects to the MySQL server on Simon Asholt Norup's Ubuntu
 * droplet.
 *
 * @author RODA & Simon Asholt Norup
 */
public class DBConnector {

    private static Connection connection;

    //Constants
    private static final String IP = "165.227.148.141"; //;
    private static final String PORT = "3306";
    public static final String DATABASE = "legoDB";
    private static final String USERNAME = "CBA_test";
    private static final String PASSWORD = "vilevilethings666";

    /**
     * Handles the actual connection to the MySQL database if not connected already.
     *
     * @return singleton connection
     * @throws Data.CustomExceptions.SQLConnectionException
     */
    public static Connection getConnection() throws SQLConnectionException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver").newInstance(); //com.mysql.cj.jdbc.Driver
                String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?allowMultiQueries=true";
                connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            throw new SQLConnectionException("The server was unable to connect to the database.");
        }
    }

    public static void setConnection(Connection con) {
        connection = con;
    }
}
