package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;
import de.haraldrichter.car_rental_service.model.InternalCarInfo;
import de.haraldrichter.car_rental_service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

//    String priceCategory,
//    String sizeCategory,
//    String type,
//    String manufacturer,
//    String model,
//    String description,
//    String transmissionType,
//    String fuelType,
//    double basePrice,
//    double kilometerPrice,
//    boolean rentedStatus,
//    int rentedDays,
//    int rentedKilometers,
//    double estimatedPrice,

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Create a Car-Object from a CarDTO-"Blueprint"
     * @param carDTO the CarDTO-Object to be turned into an actual Car-Object.
     * @return the Car-Object with the field values provided by the CarDTO-Blueprint.
     */
    public Car mapCarDTOToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setPriceCategory(carDTO.getPriceCategory());
        car.setSizeCategory(carDTO.getSizeCategory());
        car.setType(carDTO.getType());
        car.setManufacturer(carDTO.getManufacturer());
        car.setModel(carDTO.getModel());
        car.setDescription(carDTO.getDescription());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setFuelType(carDTO.getFuelType());
        car.setBasePrice(carDTO.getBasePrice());
        car.setKilometerPrice(carDTO.getKilometerPrice());
        car.setRentedStatus(carDTO.isRentedStatus());
        car.setRentedDays(carDTO.getRentedDays());
        car.setRentedKilometers(carDTO.getRentedKilometers());

        return car;
    }

    public CarDTO makeCar() {
        CarDTO carDTO = new CarDTO();
        InternalCarInfo info = new InternalCarInfo("A-OI 1349", 666, "24.09.2025", "Everything's fine.");
        carDTO.setPriceCategory("Standard");
        carDTO.setSizeCategory("Standard");
        carDTO.setType("Sedan");
        carDTO.setManufacturer("Toyota");
        carDTO.setModel("Corolla");
        carDTO.setDescription("Great Car!");
        carDTO.setTransmissionType("Manual");
        carDTO.setFuelType("Petrol");
        carDTO.setBasePrice(29.99);
        carDTO.setKilometerPrice(0.69);
        carDTO.setRentedStatus(false);
        carDTO.setRentedDays(0);
        carDTO.setRentedKilometers(0);
        carDTO.setInternalCarInfos(info);

        return carDTO;
    }

    @Override
    public String createCar(CarDTO carDTO) {
        Car car = mapCarDTOToCar(carDTO);
        carRepository.save(car);
        return "Car added successfully.";
    }
}
