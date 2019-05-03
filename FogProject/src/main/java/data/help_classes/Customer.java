/*
 */
package data.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Customer {

    private final int id;
    private final String first_name;
    private final String last_name;
    private final String address;
    private final String zipcode;
    private final String city;
    private final String phone;
    private final String email;

    public Customer(int id, String first_name, String last_name, String address, String zipcode, String city, String phone, String email) throws IllegalArgumentException {
        if (first_name == null || !first_name.matches("[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*")
                || last_name == null || !last_name.matches("[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*")
                || address == null || !address.matches("[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]+\\s\\d+")
                || zipcode == null || !zipcode.matches("\\d{4}")
                || city == null || !city.matches("[A-zÃ¦Ã¸Ã¥ÆØÅæøå]+[A-zÃ¦Ã¸Ã¥ÆØÅæøå ]*")
                || phone == null || !phone.matches("\\d{8}")
                || email == null || !email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            throw new IllegalArgumentException();
        }
        int zip_i = Integer.parseInt(zipcode);
        if (zip_i < 0 || (zip_i >= 3700 && zip_i < 4000)) { // cannot deliver to Bornholm, Færøerne or Grønland
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
