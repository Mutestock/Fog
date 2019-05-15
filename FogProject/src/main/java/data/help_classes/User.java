package data.help_classes;

/**
 * Entity class of an user.
 * Holds all nessescary information of an user.
 */
public class User {
    String username;
    String Password;

    public User(String username, String Password) {
        this.username = username;
        this.Password = Password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

}
