package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.model.User;
import de.haraldrichter.car_rental_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findUserByRoleName(String roleName) {
        return userRepository.findUserByRoleName(roleName);
    }
}
