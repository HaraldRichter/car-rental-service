package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping("/addCar")
    public ResponseEntity<String> addCar(@RequestBody CarDTO carDTO) {
        System.out.println(carDTO.getInternalCarInfo());
        String response = carService.createCar(carDTO);
        return ResponseEntity.ok(response);
    }

}
