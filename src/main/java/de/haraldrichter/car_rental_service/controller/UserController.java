package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.User;
import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showUserProfile")
    public String showUserById(@RequestParam String id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "users/user-profile";
    }

    @GetMapping("/showUpdateUserProfileForm")
    public String showUpdateUserProfileForm(@RequestParam("id") String id, Model model) {
        User data = userService.findUserById(id);
        UserDTO userDTO = new UserDTO(data);

        model.addAttribute("user", userDTO);

        return "users/user-profile-update-form";
    }


//    @GetMapping("/users/showUsersByRole")
//    public String showUsersByRole(@RequestParam String roleName) {
//        List<User> userList = userService.findUserByRoleName(roleName);
//        return "user-list";
//    }
}
