package de.haraldrichter.car_rental_service.repository;

import de.haraldrichter.car_rental_service.model.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    @Query("{ 'roles.name': ?0 }")
    List<User> findUserByRoleName(String roleName);
    @Query("{ 'email': ?0 }")
    Optional<User> findUserByEmail(String email);
}
