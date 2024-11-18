package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.model.entity.Car;
import de.haraldrichter.car_rental_service.model.entity.User;
import de.haraldrichter.car_rental_service.service.CarService;
import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/showRentedCarsOverview")
    public String showRentedCarsOverview(Model model) {
        List<Car> rentedCars = carService.getAllRentedCars();

        model.addAttribute("rentedCars", rentedCars);

        return "internals/rented-cars-overview";
    }

    @GetMapping("/showEmployeesList")
    public String showEmployeesList(Model model) {
        List<User> employees = userService.findUserByRoleName("EMPLOYEE");

        model.addAttribute("employees", employees);

        return "internals/employees-overview";
    }
}
