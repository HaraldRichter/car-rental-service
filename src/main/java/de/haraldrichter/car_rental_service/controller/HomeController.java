package de.haraldrichter.car_rental_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToShowAllCars() {
        return "redirect:/cars/showAllCars";
    }
}
