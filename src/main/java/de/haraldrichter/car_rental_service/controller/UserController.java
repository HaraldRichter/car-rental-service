package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.entity.User;
import de.haraldrichter.car_rental_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @PostMapping("/updateUserProfile")
    public String updateUserProfile(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/users/showUserProfile?id=" + userDTO.getId();
    }


    @GetMapping("/showAllCustomers")
    public String showAllCustomers(Model model) {
        List<User> userList = userService.findUserByRoleName("ROLE_CUSTOMER");
        model.addAttribute("users", userList);
        return "users/show-all-customers";
    }

    /**
     * Deletes a User account. The user can't delete his account if he is currently renting a car.
     * @param id the user accounts database document id
     * @param redirectAttributes used for parsing an error message
     * @param request used for logout after deleting the database entry
     * @param response used for logout after deleting the database entry
     * @return on success, redirection to "account successful deleted"-page; otherwise the user stays on the update form
     */
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") String id, RedirectAttributes redirectAttributes,
                             HttpServletRequest request, HttpServletResponse response) {

        // Check if user is currently renting a car
        User user = userService.findUserById(id);
        if (user.getRentedCars() != null && !user.getRentedCars().isEmpty()) {
            redirectAttributes.addFlashAttribute("deleteError", "Sorry, you can't delete your profile while you are renting a car!");
            return "redirect:/users/showUpdateUserProfileForm?id=" + id;
        }

        // Delete user if he's not currently renting a car
        userService.deleteUserById(id);

        // Logout user
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/auth/showAccountDeletionSuccessPage";
    }
}
