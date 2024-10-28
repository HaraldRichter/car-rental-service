package de.haraldrichter.car_rental_service.model.entity;

import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * Represents a customer who can rent a car.
 * While everyone can browse through the available cars, only registered customers
 * can actually rent one.
 */
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Field(name = "job_title")
    private String jobTitle;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private String street;
    @Field(name = "postal_code")
    private int postalCode;
    private String town;
    private Set<Role> roles;
    @Field(name = "rented_cars")
    private Set<Car> rentedCars;


    // === CONSTRUCTORS ===

    /**
     * No-args constructor, just in case we need it.
     */
    public User() {}

    /**
     * The standard constructor for user Objects.
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email, also serves as 'username' for authentication purposes
     * @param password password, gets hashed before being stored in the database
     * @param street user's street and house number (customers: their billing address)
     * @param postalCode user's postal code (customers: billing address)
     * @param town user's hometown (customers: billing address)
     * @param roles authorization role(s); there are three roles: CUSTOMER, EMPLOYEE and ADMIN
     * @param rentedCars the cars this user has currently reserved/rented; only customers can rent cars
     */
    @PersistenceCreator
    public User(String id,
                String firstName,
                String lastName,
                String email,
                String jobTitle,
                String password,
                String street,
                int postalCode,
                String town,
                Set<Role> roles,
                Set<Car> rentedCars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.password = password;
        this.street = street;
        this.postalCode = postalCode;
        this.town = town;
        this.roles = roles;
        this.rentedCars = rentedCars;
    }

    /**
     * If there is no user with the admin-role, one is automatically created with this Constructor at the start of the program.
     * Further details can be added later manually.
     * @param jobTitle the user's job title, e.g. "admin"; used for login
     * @param email the user's email
     * @param password password set by the user or an admin creating the account; gets hashed before being stored in the database
     * @param roles authorization role; can be ROLE_EMPLOYEE and/or ROLE_ADMIN
     */
    public User(String jobTitle, String email, String password, Set<Role> roles) {
        this.jobTitle = jobTitle;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Constructor for directly mapping a UserDTO-Object to a User-Object,
     * used on account creation
     * @param userDTO the UserDTO that is the User's "blueprint"
     */
    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.jobTitle = userDTO.getJobTitle();
        this.password = userDTO.getPassword();
        this.street = userDTO.getStreet();
        this.postalCode = userDTO.getPostalCode();
        this.town = userDTO.getTown();
        this.roles = userDTO.getRoles();
        this.rentedCars = userDTO.getRentedCars();
    }


    // === SETTERS AND GETTERS ===
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Car> getRentedCars() {
        return rentedCars;
    }

    public void setRentedCars(Set<Car> rentedCars) {
        this.rentedCars = rentedCars;
    }

    // === TO STRING ===
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", street='" + street + '\'' +
                ", postalCode=" + postalCode +
                ", town='" + town + '\'' +
                ", roles=" + roles +
                ", rentedCars=" + rentedCars +
                '}';
    }
}
