package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.User;

import java.util.List;

public interface UserService {
    List<User> findUserByRoleName(String roleName);


    void registerNewUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    User findUserById(String id);
}
