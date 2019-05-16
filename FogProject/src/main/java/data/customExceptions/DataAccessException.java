package data.customExceptions;

/**
 * Exception thrown whenever any exception occurs as a DAO-class attempts to access data from an external source,
 * e.g. a MySQL database or a JSON text file.
 * It is recommended to pass either the cause of the Exception onwards in the system (e.g. from a SQLException)
 * so that potential catch-statements in frontend layers can define actions according to exception type,
 * or the message of the specific cause Exception (as opposed to this general exception), so that this specialized message can be displayed.
 * @author Emil Svensmark
 */

public class DataAccessException extends Exception {

    public DataAccessException() {
    }
    public DataAccessException(String string) {
        super(string);
    }
    public DataAccessException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    public DataAccessException(Throwable thrwbl) {
        super(thrwbl);
    }
}