package de.haraldrichter.car_rental_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller handles user registration and login.
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage() {
        return "auth/register";
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
