package de.haraldrichter.car_rental_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.haraldrichter.car_rental_service.model.Car;
import de.haraldrichter.car_rental_service.model.CarInternals;
import org.springframework.data.annotation.Id;

public class CarDTO {
    @Id
    private String id;
    @JsonProperty("price_category")
    private String priceCategory;
    private String type;
    private String manufacturer;
    private String model;
    private String description;
    @JsonProperty("transmission_type")
    private String transmissionType;
    @JsonProperty("fuel_type")
    private String fuelType;
    @JsonProperty("base_price")
    private double basePrice;
    @JsonProperty("kilometer_price")
    private double kilometerPrice;
    @JsonProperty("is_available")
    private boolean isAvailable;
    @JsonProperty("rented_days")
    private int rentedDays;
    @JsonProperty("rented_kilometres")
    private int rentedKilometers;
    private double estimatedPrice;
    @JsonProperty("internal_info")
    private CarInternals carInternals;

    // === CONSTRUCTORS ===
    public CarDTO() {}

    // This constructor can be used if we read a Car from the DB but need a CarDTO for further processing.
    public CarDTO(Car car) {
        this.id = car.getId();
        this.priceCategory = car.getPriceCategory();
        this.type = car.getType();
        this.manufacturer = car.getManufacturer();
        this.model = car.getModel();
        this.description = car.getDescription();
        this.transmissionType = car.getTransmissionType();
        this.fuelType = car.getFuelType();
        this.basePrice = car.getBasePrice();
        this.kilometerPrice = car.getKilometerPrice();
        this.isAvailable =car.isAvailable();
        this.rentedDays = car.getRentedDays();
        this.rentedKilometers = car.getRentedKilometers();
        this.carInternals = car.getCarInternals();
    }

    public CarDTO(String priceCategory,
                  String type,
                  String manufacturer,
                  String model,
                  String description,
                  String transmissionType,
                  String fuelType,
                  double basePrice,
                  double kilometerPrice,
                  boolean isAvailable,
                  int rentedDays,
                  int rentedKilometers,
                  CarInternals carInternals) {
        this.priceCategory = priceCategory;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.basePrice = basePrice;
        this.kilometerPrice = kilometerPrice;
        this.isAvailable = isAvailable;
        this.rentedDays = rentedDays;
        this.rentedKilometers = rentedKilometers;
        this.carInternals = carInternals;
    }

    // === GETTERS AND SETTERS ===
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
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

    public CarInternals getCarInternals() {
        return carInternals;
    }

    public void setCarInternals(CarInternals carInternals) {
        this.carInternals = carInternals;
    }

    // === TO STRING ===
    @Override
    public String toString() {
        return "CarDTO{" +
                "id='" + id + '\'' +
                ", priceCategory='" + priceCategory + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", basePrice=" + basePrice +
                ", kilometerPrice=" + kilometerPrice +
                ", isAvailable=" + isAvailable +
                ", rentedDays=" + rentedDays +
                ", rentedKilometers=" + rentedKilometers +
                ", estimatedPrice=" + estimatedPrice +
                ", carInternals=" + carInternals +
                '}';
    }
}
