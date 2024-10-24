package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;

import java.util.List;

public interface CarService {
   String createCar(CarDTO carDTO);

   List<Car> getAllCars();

   Car getCarById(String id);
}
