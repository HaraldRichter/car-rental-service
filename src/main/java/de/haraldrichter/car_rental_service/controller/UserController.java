package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users/showUsersByRole")
//    public String showUsersByRole(@RequestParam String roleName) {
//        List<User> userList = userService.findUserByRoleName(roleName);
//        return "user-list";
//    }
}
