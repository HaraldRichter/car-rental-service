package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Provides standard database access methods like findAll(), findById() etc. simply by extending MongoRepository.
 * Car: The model administered by this repository. The Java-class defines the structure of the document inside the database.
 * String: The data type of the document's primary id, by default String.
 */
public interface CarRepository extends MongoRepository<Car, String>, CarRepositoryCustom {

    List<Car> findByIsAvailableFalse();
}
