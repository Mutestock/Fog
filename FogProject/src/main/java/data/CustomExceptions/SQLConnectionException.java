package data.CustomExceptions;

import java.sql.SQLException;

/**
 * Thrown when an attempt to connect to a database fails.
 * Currently thrown by DBConnector class and caught by DAO-classes.
 * @author Simon Asholt Norup
 */
public class SQLConnectionException extends SQLException {


    public SQLConnectionException() {
    }

    public SQLConnectionException(String string) {
        super(string);
    }

    public SQLConnectionException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public SQLConnectionException(Throwable thrwbl) {
        super(thrwbl);
    }
}