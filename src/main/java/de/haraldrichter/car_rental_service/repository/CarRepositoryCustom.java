package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.entity.Car;

import java.util.List;


/**
 * Provides custom database access methods.
 * Separated from CarRepository for better code maintainability.
 */
public interface CarRepositoryCustom {
    List<Car> findCarsByCriteria(String category, String type, Boolean isAvailable);
}
