package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.entity.Role;
import de.haraldrichter.car_rental_service.model.entity.User;
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
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUserByRoleName(String roleName) {
        return userRepository.findUserByRoleName(roleName);
    }

    @Override
    public void registerNewUser(UserDTO userDTO, boolean isEmployee) {
        String role = isEmployee ? "ROLE_EMPLOYEE" : "ROLE_CUSTOMER";

        // Check if email is already taken:
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        // Map userDTO to User:
        User user = new User(userDTO);

        // Encrypt password:
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Add role
        Role userRole = roleRepository.findByName(role);
        user.setRoles(Collections.singleton(userRole));

        // Save new User to the database:
        userRepository.save(user);
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        // Get User from Database:
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Set only Fields with new values, leave other fields like the roles alone:
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setJobTitle(userDTO.getJobTitle());
        user.setStreet(userDTO.getStreet());
        user.setPostalCode(userDTO.getPostalCode());
        user.setTown(userDTO.getTown());
        user.setRentedCars(userDTO.getRentedCars());

        userRepository.save(user);
    }
}
