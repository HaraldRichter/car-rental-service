package de.haraldrichter.car_rental_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Car for rent
 * id: Database document id (auto-generated)
 * priceCategory: General price category: Economy, Standard, Premium, Luxury
 * sizeCategory: Compact, Standard, Large
 * type: Sedan, Convertible, Sports Car, Coupe, Pick-Up, Limousine, Minivan, SUV, Micro Car
 * manufacturer: self-explanatory
 * model: self-explanatory
 * description: A short description for the customer
 * transmission_type: Manual, Automatic
 * fuelType: Petrol, Diesel, Electric, Hybrid
 * basePrice: Base price per day for renting this car, including 100 km/day
 * kilometerPrice: Price for kilometers exceeding the limit of 100 km/day
 * rentedStatus: Is the car currently rented?
 * rentedDays: For how many days is the car rented? (default: 0)
 * rentedKilometers: For how many expected kilometers is the car rented? (default: 0)
 * estimatedPrice: Price estimation for the customer, not stored in the database
 * internalCarInfos: Information that is not for the public
 */
@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    @Field(name = "price_category")
    private String priceCategory;
    @Field(name = "size_category")
    private String sizeCategory;
    @Field(name = "type")
    private String type;
    @Field(name = "manufacturer")
    private String manufacturer;
    @Field(name = "model")
    private String model;
    @Field(name = "description")
    private String description;
    @Field(name = "transmission_type")
    private String transmissionType;
    @Field(name = "fuel_type")
    private String fuelType;
    @Field(name = "base_price")
    private double basePrice;
    @Field(name = "kilometer_price")
    private double kilometerPrice;
    @Field(name = "rented_status")
    private boolean rentedStatus;
    @Field(name = "rented_days")
    private int rentedDays;
    @Field(name = "rented_kilometers")
    private int rentedKilometers;
    @Transient
    private double estimatedPrice;
    @Field(name = "internal_info")
    private InternalCarInfo internalCarInfo;

    public Car() {};

    @PersistenceCreator
    public Car(String priceCategory,
               String sizeCategory,
               String type,
               String manufacturer,
               String model,
               String description,
               String transmissionType,
               String fuelType,
               double basePrice,
               double kilometerPrice,
               boolean rentedStatus,
               int rentedDays,
               int rentedKilometers,
               InternalCarInfo internalCarInfo) {
        this.priceCategory = priceCategory;
        this.sizeCategory = sizeCategory;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.basePrice = basePrice;
        this.kilometerPrice = kilometerPrice;
        this.rentedStatus = rentedStatus;
        this.rentedDays = rentedDays;
        this.rentedKilometers = rentedKilometers;
        this.internalCarInfo = internalCarInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getSizeCategory() {
        return sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getKilometerPrice() {
        return kilometerPrice;
    }

    public void setKilometerPrice(double kilometerPrice) {
        this.kilometerPrice = kilometerPrice;
    }

    public boolean isRentedStatus() {
        return rentedStatus;
    }

    public void setRentedStatus(boolean rentedStatus) {
        this.rentedStatus = rentedStatus;
    }

    public int getRentedDays() {
        return rentedDays;
    }

    public void setRentedDays(int rentedDays) {
        this.rentedDays = rentedDays;
    }

    public int getRentedKilometers() {
        return rentedKilometers;
    }

    public void setRentedKilometers(int rentedKilometers) {
        this.rentedKilometers = rentedKilometers;
    }

    public double getEstimatedPrice() {
        estimatedPrice = rentedDays * basePrice + rentedKilometers * kilometerPrice;
        return estimatedPrice;
    }

    public InternalCarInfo getInternalCarInfo() {
        return internalCarInfo;
    }

    public void setInternalCarInfo(InternalCarInfo internalCarInfo) {
        this.internalCarInfo = internalCarInfo;
    }

}

