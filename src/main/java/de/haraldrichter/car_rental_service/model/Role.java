package de.haraldrichter.car_rental_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the different user roles for authorization.
 * There are three roles at the moment: ROLE_ADMIN, ROLE_EMPLOYEE and ROLE_CUSTOMER.
 */
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String name;

    // === CONSTRUCTORS ===
    public Role(String name) {
        this.name = name;
    }

    // === GETTERS AND SETTERS ===


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // === TO STRING ===
    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
