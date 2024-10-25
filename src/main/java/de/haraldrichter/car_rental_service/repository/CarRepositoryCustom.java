package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.Car;

import java.util.List;

public interface CarRepositoryCustom {
    List<Car> findCarsByCriteria(String category, String type, Boolean isAvailable);
}
