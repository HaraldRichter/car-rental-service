package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findUserByRoleName(String roleName);

    void registerNewUser(UserDTO userDTO, boolean isEmployee);

    void updateUser(UserDTO userDTO);

    User findUserById(String id);

    void deleteUserById(String id);
}
