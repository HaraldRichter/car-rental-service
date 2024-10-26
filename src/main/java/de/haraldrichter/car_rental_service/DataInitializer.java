package de.haraldrichter.car_rental_service;

import de.haraldrichter.car_rental_service.model.Role;
import de.haraldrichter.car_rental_service.model.User;
import de.haraldrichter.car_rental_service.repository.RoleRepository;
import de.haraldrichter.car_rental_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The DataInitializer.run() method is automatically executed on start of the application.
 * It ensures that the role-entries necessary for user authorization are available in the database
 * as well as there is always at least one admin account. If the roles or the admin account are missing,
 * it creates them automatically.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String[] args) {
        // Initialize Roles, if not present
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE");
        if (employeeRole == null) {
            employeeRole = new Role("ROLE_EMPLOYEE");
            roleRepository.save(employeeRole);
        }

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER");
        if (customerRole == null) {
            customerRole = new Role("ROLE_CUSTOMER");
            roleRepository.save(customerRole);
        }

        // Create admin-user, if there is not at least one admin-account already
        if (userRepository.findUserByRoleName("ROLE_ADMIN").isEmpty()) {
            User admin = new User("admin", "admin@companymail.com", passwordEncoder.encode("admin123"), Set.of(employeeRole, adminRole));
            userRepository.save(admin);
        }
    }
}