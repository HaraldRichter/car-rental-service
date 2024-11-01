package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
