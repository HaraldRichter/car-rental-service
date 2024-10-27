package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.dto.UserDTO;
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

        // Benutzer registrieren
        try {
            userService.registerNewUser(userDTO);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", null, e.getMessage());
            return "auth/register-new-customer";
        }

        // Erfolgreiche Registrierung: Weiterleitung zur Login-Seite
        return "redirect:/auth/showLoginPage?registered";
    }

    @GetMapping("/showLogoutSuccessPage")
    public String showLogoutSuccessPage() {
        return "auth/logout-success";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "auth/access-denied";
    }
}
