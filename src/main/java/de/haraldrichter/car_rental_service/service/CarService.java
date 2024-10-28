package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.model.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.entity.Car;

import java.util.List;

public interface CarService {

   void saveCar(CarDTO carDTO);

   List<Car> getAllCars();

   Car getCarById(String id);

   void deleteCarById(String id);

   List<Car> getCarsByQuery(String category, String type, Boolean isAvailable);
}
