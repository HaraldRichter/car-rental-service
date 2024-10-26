package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepositoryCustom {
    @Query("{ 'roles.name': ?0 }")
    List<User> findUserByRoleName(String roleName);
}
