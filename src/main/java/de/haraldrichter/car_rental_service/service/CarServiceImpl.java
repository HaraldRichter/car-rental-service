package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.model.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.entity.Car;
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

    @Override
    public void saveCar(CarDTO carDTO) {
        Car car;

        // Get car data from DB, if car is already present.
        Optional<Car> result = carRepository.findById(carDTO.getId());

        // If car is already present (= you want to update an existing car),
        // get the results form the DB.
        // Otherwise, (= you want to add a new car to the DB) create a fresh
        // car object.
        car = result.orElseGet(Car::new);

        // Fill the categories to be updated from the carDTO object.
        // Note that the car's id is never set here - it either remains the
        // same, or is automatically created when we add a new car to the DB.
        car.setPriceCategory(carDTO.getPriceCategory());
        car.setManufacturer(carDTO.getManufacturer());
        car.setModel(carDTO.getModel());
        car.setType(carDTO.getType());
        car.setDescription(carDTO.getDescription());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setFuelType(carDTO.getFuelType());
        car.setBasePrice(carDTO.getBasePrice());
        car.setKilometerPrice(carDTO.getKilometerPrice());
        car.setAvailable(carDTO.isAvailable());
        car.setRentedDays(carDTO.getRentedDays());
        car.setRentedKilometers(carDTO.getRentedKilometers());
        car.setCarInternals(carDTO.getCarInternals());
        car.setRentedByCustomer(carDTO.getRentedByCustomer());

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
        Car car;
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
