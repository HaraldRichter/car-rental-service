package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;
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
        Car car = new Car(carDTO);
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
