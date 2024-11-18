package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller handles user registration and login.
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage() {
        return "auth/register-new-customer";
    }

    @PostMapping("/registerNewUser")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        // Check if password confirmation is correct
        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) {
            result.rejectValue("confirmPassword", null, "Passwords don't match");
            return "auth/register-new-customer";
        }

        // Register the user
        try {
            userService.registerNewUser(userDTO, false);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", null, e.getMessage());
            return "auth/register-new-customer";
        }

        // After registration: redirect to login
        // TODO: Automatically log the user in after successful registration
        return "redirect:/auth/showLoginPage?registered";
    }

    @GetMapping("/showCreateEmployeePage")
    public String showCreateEmployeePage() {
        return "auth/register-new-employee";
    }


    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        // Check if password confirmation is correct
        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirmation())) {
            result.rejectValue("confirmPassword", null, "Passwords don't match");
            return "auth/register-new-employee";
        }

        // Create Account
        try {
            userService.registerNewUser(userDTO, true);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", null, e.getMessage());
            return "auth/register-new-employee";
        }

        // Redirect to Employee Overview
        return "redirect:/internals/showEmployeesList";
    }

    @GetMapping("/showLogoutSuccessPage")
    public String showLogoutSuccessPage() {
        return "auth/logout-success";
    }

    @GetMapping("/showAccountDeletionSuccessPage")
    public String showAccountDeletionSuccessPage() {
        return "auth/delete-account-success";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "auth/access-denied";
    }
}
