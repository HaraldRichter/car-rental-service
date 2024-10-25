package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
