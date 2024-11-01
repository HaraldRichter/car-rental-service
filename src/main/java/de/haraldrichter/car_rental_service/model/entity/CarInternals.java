package de.haraldrichter.car_rental_service.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Field;

public class CarInternals {
    @Field(name = "license_plate")
    @JsonProperty("license_plate")
    private String licensePlate;
    @Field(name = "mileage")
    private int mileage;
    @Field(name = "next_inspection")
    @JsonProperty("next_inspection")
    private String nextInspection;
    @Field(name = "notes")
    private String notes;


    public CarInternals() {
    }
    @PersistenceCreator
    public CarInternals(String licensePlate, int mileage, String nextInspection, String notes) {
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.nextInspection = nextInspection;
        this.notes = notes;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(String nextInspection) {
        this.nextInspection = nextInspection;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
