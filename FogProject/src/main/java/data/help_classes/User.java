package data.help_classes;

/**
 *
 * @author Lukas Bjørnvad
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
