package de.haraldrichter.car_rental_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.haraldrichter.car_rental_service.model.InternalCarInfo;

public class CarDTO {
    private String id;
    private String priceCategory;
    private String sizeCategory;
    private String type;
    private String manufacturer;
    private String model;
    private String description;
    private String transmissionType;
    private String fuelType;
    private double basePrice;
    private double kilometerPrice;
    private boolean rentedStatus;
    private int rentedDays;
    private int rentedKilometers;
    private double estimatedPrice;
    @JsonProperty("internal_info")
    private InternalCarInfo internalCarInfo;

    public CarDTO() {}

    public CarDTO(String priceCategory,
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
                  double estimatedPrice,
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
        this.estimatedPrice = estimatedPrice;
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
        if (rentedKilometers < (rentedDays * 100)) {
            this.rentedKilometers = 0;
        } else {
            this.rentedKilometers = rentedKilometers - (rentedDays * 100);
        }
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
