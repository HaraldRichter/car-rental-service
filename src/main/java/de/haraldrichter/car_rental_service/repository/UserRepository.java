package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Provides standard database access methods like findAll(), findById() etc. simply by extending MongoRepository.
 * Customer: The model administered by this repository. The Java-class defines the structure of the document inside the database.
 * String: The data type of the document's primary id, by default String.
 */
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
}
