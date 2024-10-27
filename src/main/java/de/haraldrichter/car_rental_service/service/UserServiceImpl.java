package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.Role;
import de.haraldrichter.car_rental_service.model.User;
import de.haraldrichter.car_rental_service.repository.RoleRepository;
import de.haraldrichter.car_rental_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findUserByRoleName(String roleName) {
        return userRepository.findUserByRoleName(roleName);
    }

    @Override
    public void registerNewUser(UserDTO userDTO) {

        // Check if email is already taken:
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        // Map userDTO to User:
        User user = new User(userDTO);

        // Encrypt password:
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Add standard role
        Role userRole = roleRepository.findByName("ROLE_CUSTOMER");
        user.setRoles(Collections.singleton(userRole));

        // Save new User to the database:
        userRepository.save(user);
        System.out.println("User has been saved to the database: " + user.getEmail());
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = new User(userDTO);
        userRepository.save(user);
    }


}
