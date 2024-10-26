package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;
import de.haraldrichter.car_rental_service.model.CarInternals;
import de.haraldrichter.car_rental_service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

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
        car.setId(carDTO.getId());
        car.setPriceCategory(carDTO.getPriceCategory());
        car.setType(carDTO.getType());
        car.setManufacturer(carDTO.getManufacturer());
        car.setModel(carDTO.getModel());
        car.setDescription(carDTO.getDescription());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setFuelType(carDTO.getFuelType());
        car.setBasePrice(carDTO.getBasePrice());
        car.setKilometerPrice(carDTO.getKilometerPrice());
        car.setAvailable(carDTO.isAvailable());
        car.setRentedDays(carDTO.getRentedDays());
        car.setRentedKilometers(carDTO.getRentedKilometers());

        if (carDTO.getCarInternals() != null) {
            System.out.println("CarInternals is not null and being set.");
            CarInternals carInternals = new CarInternals();
            carInternals.setLicensePlate(carDTO.getCarInternals().getLicensePlate());
            carInternals.setMileage(carDTO.getCarInternals().getMileage());
            carInternals.setNextInspection(carDTO.getCarInternals().getNextInspection());
            carInternals.setNotes(carDTO.getCarInternals().getNotes());
            car.setCarInternals(carInternals);
        } else {
            System.out.println("CarInternals is null.");
        }

        return car;
    }

    @Override
    public void saveCar(CarDTO carDTO) {
        Car car = mapCarDTOToCar(carDTO);
        carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(String id) {
        // Try to get the data from the database; Optional in case the query fails
        Optional<Car> result = carRepository.findById(id);

        // Check if we got data back; if not, throw error
        Car car = null;
        if (result.isPresent()) {
            car = result.get();
        }
        else {
            // we didn't find the car
            throw new RuntimeException("This car doesn't exist in the database! Invalid id: " + id);
        }

        return car;
    }


    @Override
    public void deleteCarById(String id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getCarsByQuery(String category, String type, Boolean isAvailable) {
        return carRepository.findCarsByCriteria(category, type, isAvailable);
    }
}
