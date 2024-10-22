package de.haraldrichter.car_rental_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("date", java.time.LocalDateTime.now());
        return "home";
    }
}
