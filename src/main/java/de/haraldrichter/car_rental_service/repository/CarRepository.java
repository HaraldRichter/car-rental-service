package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String>, CarRepositoryCustom {

}
