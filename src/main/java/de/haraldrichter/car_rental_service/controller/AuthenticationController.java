package de.haraldrichter.car_rental_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller handles user registration and login.
 */
@Controller
public class AuthenticationController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "users/login";
    }
}
