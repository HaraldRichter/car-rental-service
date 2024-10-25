package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {

    @Query("{\"price_category\": \"?0\"}")
    List<Car> showCarsByQuery(String category);

}
