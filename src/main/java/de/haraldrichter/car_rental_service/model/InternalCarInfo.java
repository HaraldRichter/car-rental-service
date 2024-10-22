package de.haraldrichter.car_rental_service.model;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Field;

public class InternalCarInfo {
    @Field(name = "license_plate")
    private String licensePlate;
    @Field(name = "mileage")
    private int mileage;
    @Field(name = "next_inspection")
    private String nextInspection;
    @Field(name = "notes")
    private String notes;

    @PersistenceCreator
    public InternalCarInfo() {
    }
    public InternalCarInfo(String licensePlate, int mileage, String nextInspection, String notes) {
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.nextInspection = nextInspection;
        this.notes = notes;
    }
}
