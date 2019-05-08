package data;

import data.customExceptions.DataAccessException;
import data.help_classes.User;

/**
 *
 * @author Lukas Bjørnvad
 */
public interface DataMapperUserInterface  {
    public void addUser(User user) throws DataAccessException;
    public User getUser(String username) throws DataAccessException;
    
}
