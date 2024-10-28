package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.service.CarService;
import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internals")
public class InternalsController {
    private final UserService userService;
    private final CarService carService;

    @Autowired
    public InternalsController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/showCustomerCarOverview")
    public String showCustomerCarOverview() {
        return "internals/customer-car-overview";
    }
}
