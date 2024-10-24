package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;
import de.haraldrichter.car_rental_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping("/addCar")
    public String addCar(@ModelAttribute("car") CarDTO carDTO) {
        System.out.println(carDTO.getInternalCarInfo());
        carService.createCar(carDTO);
        return "redirect:/cars/showAllCars";
    }



    @GetMapping("/showAddCarForm")
    public String showAddCarForm(Model model) {
        CarDTO carDTO = new CarDTO();
         model.addAttribute("car", carDTO);
         return "cars/add-car-form";
    }

    @GetMapping("/showAllCars")
    public String showAllCars(Model model) {
        List<Car> cars = carService.getAllCars();

        model.addAttribute("cars", cars);

        return "cars/show-all-cars";
    }

    @GetMapping("/showCarById")
    public String showCarById(@RequestParam String id, Model model) {
        Car car = carService.getCarById(id);

        model.addAttribute("car", car);

        return "cars/show-car-details";

    }

}
