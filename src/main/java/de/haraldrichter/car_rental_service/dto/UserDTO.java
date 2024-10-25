package de.haraldrichter.car_rental_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.haraldrichter.car_rental_service.model.User;
import org.springframework.data.annotation.Id;

public class UserDTO {
    @Id
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String street;
    private String password;
    @JsonProperty("postal_code")
    private int postalCode;
    private String town;

    private String role;


    // === CONSTRUCTORS ===

    /**
     * Standard no-args constructor just in case it is needed...
     */
    public UserDTO() {}

    /**
     * Constructor to directly map a User-Object to a new UserDTO-Object.
     * Useful if we want to create a UserDTO with data from the database.
     * @param user the User object to be mapped to the new UserDTO
     */
    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.street = user.getStreet();
        this.postalCode = user.getPostalCode();
        this.town = user.getTown();
        this.role = user.getRole();
    }

    /**
     * Standard constructor. Doesn't include the id-parameter, as this is normally set automatically inside the database.
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param street
     * @param postalCode
     * @param town
     * @param role
     */
    public UserDTO(String firstName, String lastName, String email, String street, String password, int postalCode, String town, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.street = street;
        this.postalCode = postalCode;
        this.town = town;
        this.role = role;
    }


    //=== GETTERS AND SETTERS ===
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    // === TO STRING ===

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", password='" + password + '\'' +
                ", postalCode=" + postalCode +
                ", town='" + town + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
