package de.haraldrichter.car_rental_service.model.entity;

import de.haraldrichter.car_rental_service.model.dto.CarDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a car that is available for rent.
 *
 * id: Database document id (auto-generated)
 * priceCategory: Economy, Standard, Premium, Luxury
 * type: Sedan, Convertible, Sports Car, Coupe, Pick-Up, Limousine, Minivan, SUV, Micro Car
 * manufacturer: The brand
 * model: The car model
 * description: A short and absolutely serious description of the car
 * transmissionType: Manual, Automatic
 * fuelType: Petrol, Diesel, Electric, Hybrid
 * basePrice: Base price per day for renting this car
 * kilometerPrice: Price per kilometer
 * isAvailable: Is the car currently available for rent?
 * rentedDays: For how many days is the car rented? (default: 0)
 * rentedKilometers: For how many expected kilometers is the car rented? (default: 0)
 * estimatedPrice: Price estimation for the customer, not stored in the database
 * CarInternals: Internal information that is not for the public and can only be seen by the Admin
 */
@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    @Field(name = "price_category")
    private String priceCategory;
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
    @Field(name = "is_available")
    private boolean isAvailable;
    @Field(name = "rented_days")
    private int rentedDays;
    @Field(name = "rented_kilometers")
    private int rentedKilometers;
    @Transient
    private double estimatedPrice;
    @Field(name = "internal_info")
    private CarInternals carInternals;
    @Field(name = "rented_by_customer")
    private User rentedByCustomer;

    // === CONSTRUCTORS ===
    public Car() {};

    @PersistenceCreator
    public Car(String priceCategory,
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
               CarInternals carInternals,
               User rentedByCustomer) {
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
        this.rentedByCustomer = rentedByCustomer;
    }

    /**
     * Constructor to directly map a CarDTO-Object to a new Car object.
     * @param carDTO the 'blueprint' CarDTO
     */
    public Car(CarDTO carDTO) {
        this.id = carDTO.getId();
        this.priceCategory = carDTO.getPriceCategory();
        this.type = carDTO.getType();
        this.manufacturer = carDTO.getManufacturer();
        this.model = carDTO.getModel();
        this.description = carDTO.getDescription();
        this.transmissionType = carDTO.getTransmissionType();
        this.fuelType = carDTO.getFuelType();
        this.basePrice = carDTO.getBasePrice();
        this.kilometerPrice = carDTO.getKilometerPrice();
        this.isAvailable = carDTO.isAvailable();
        this.rentedDays = carDTO.getRentedDays();
        this.rentedKilometers = carDTO.getRentedKilometers();
        this.carInternals = carDTO.getCarInternals();
        this.rentedByCustomer = carDTO.getRentedByCustomer();
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

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public CarInternals getCarInternals() {
        return carInternals;
    }

    public void setCarInternals(CarInternals carInternals) {
        this.carInternals = carInternals;
    }

    public User getRentedByCustomer() {
        return rentedByCustomer;
    }

    public void setRentedByCustomer(User rentedByCustomer) {
        this.rentedByCustomer = rentedByCustomer;
    }

    // === TO STRING ===
    @Override
    public String toString() {
        return "Car{" +
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
                ", rentedByCustomer=" + rentedByCustomer +
                '}';
    }
}

