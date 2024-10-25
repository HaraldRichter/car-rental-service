package de.haraldrichter.car_rental_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a customer who can rent a car.
 * While everyone can browse through the available cars, only registered customers
 * can actually rent one.
 */
@Document(collection = "users")
public class User {
    @Id
    private String id;
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
    private String role;


    // === CONSTRUCTORS ===
    public User() {}
    @PersistenceCreator
    public User(String id, String firstName, String lastName, String email, String password, String street, int postalCode, String town, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.street = street;
        this.postalCode = postalCode;
        this.town = town;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // === TO STRING ===
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", street='" + street + '\'' +
                ", postalCode=" + postalCode +
                ", town='" + town + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
