package de.haraldrichter.car_rental_service.controller;

import de.haraldrichter.car_rental_service.model.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.dto.RentCarFormData;
import de.haraldrichter.car_rental_service.model.dto.UserDTO;
import de.haraldrichter.car_rental_service.model.entity.Car;
import de.haraldrichter.car_rental_service.model.entity.User;
import de.haraldrichter.car_rental_service.service.CarService;
import de.haraldrichter.car_rental_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }


    @GetMapping("/showAddCarForm")
    public String showAddCarForm(Model model) {
        CarDTO carDTO = new CarDTO();
         model.addAttribute("car", carDTO);
         return "cars/add-or-update-car-form";
    }

    @GetMapping("/showUpdateCarForm")
    public String showUpdateCarForm(@RequestParam("id") String id, Model model) {
        Car data = carService.getCarById(id);
        CarDTO carDTO = new CarDTO(data);

        model.addAttribute("car", carDTO);

        return "cars/add-or-update-car-form";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute("car") CarDTO carDTO) {
        carService.saveCar(carDTO);
        return "redirect:/cars/showAllCars";
    }

    /**
     * Fetches all cars from the database and displays their base data.
     * Provides the data for the landing page.
     * @param model model used by Thymeleaf to display the html-page
     * @return display a table of all cars on show-all-cars.html
     */
    @GetMapping("/showAllCars")
    public String showAllCars(Model model) {
        List<Car> cars = carService.getAllCars();

        model.addAttribute("cars", cars);

        return "cars/show-all-cars";
    }

    /**
     * Fetches cars from database depending on the search filters set by the user.
     * The user can set filters in any combination. If no filters are set, all cars
     * are displayed.
     * @param model model used by Thymeleaf to display the html-page
     * @param category filter by the car's price category
     * @param type filter by the car's type
     * @param isAvailable filter by showing only cars that are currently available
     * @return show filtered results on show-all-cars.html
     */
    @GetMapping("/showCarsByQuery")
    public String showCarsByQuery(Model model,
                                  @RequestParam(required = false) String category,
                                  @RequestParam(required = false) String type,
                                  @RequestParam(required = false) Boolean isAvailable) {
        List<Car> cars = carService.getCarsByQuery(category, type, isAvailable);
        model.addAttribute("cars", cars);

        return "cars/show-all-cars";
    }

    /**
     * Fetches and shows detailed data of a specific car document.
     * @param id document id
     * @param model model used by Thymeleaf to display the html-page
     * @return display show-car-details.html
     */
    @GetMapping("/showCarById")
    public String showCarById(@RequestParam String id, Model model) {
        Car car = carService.getCarById(id);

        model.addAttribute("car", car);

        return "cars/show-car-details";
    }

    /**
     * Deletes a car document from DB and redirects the user to the overview page.
     * @param id the document id
     * @return redirect to overview (show-all-cars.html)
     */
    @GetMapping("/deleteCar")
    public String deleteCarById(@RequestParam String id) {
        carService.deleteCarById(id);
        return "redirect:/cars/showAllCars";
    }

    @GetMapping("/showRentCarPage")
    public String showRentCarPage(@RequestParam String carId, @RequestParam String userId, Model model) {
        Car car = carService.getCarById(carId);
        User user = userService.findUserById(userId);

        RentCarFormData rentCarFormData = new RentCarFormData(new CarDTO(car), new UserDTO(user));

        model.addAttribute("rentCarFormData", rentCarFormData);

        return "cars/rent-car";
    }

    @PostMapping("/rentCar")
    public String rentCar(@ModelAttribute("rentCarFormData") RentCarFormData rentCarFormData) {

        // Car- und User-Objekte laden
        Car car = carService.getCarById(rentCarFormData.getCarDTO().getId());
        User user = userService.findUserById(rentCarFormData.getUserDTO().getId());

        //  1: Car zur Liste "rentedCars" des Users hinzufügen
        if (user.getRentedCars() == null) {
            user.setRentedCars(new HashSet<>());
        }
        user.getRentedCars().add(car);

        //  2: "rentedDays" und "rentedKilometers" des Car-Objekts aktualisieren
        car.setRentedDays(rentCarFormData.getCarDTO().getRentedDays());
        car.setRentedKilometers(rentCarFormData.getCarDTO().getRentedKilometers());
        car.setRentedByCustomer(user);

        //  3: "isAvailable" auf false setzen
        car.setAvailable(false);

        // Änderungen in der Datenbank speichern
        carService.saveCar(new CarDTO(car));
        userService.updateUser(new UserDTO(user));

        return "redirect:/cars/showCarById?id=" + rentCarFormData.getCarDTO().getId();
    }

    @PostMapping("/cancelReservation")
    public String cancelReservation(@RequestParam String carId, @RequestParam String userId) {
        // Car-Objekt laden
        CarDTO car = new CarDTO(carService.getCarById(carId));

        // User-Objekt laden
        UserDTO user = new UserDTO(userService.findUserById(userId));

        // Car-Reservierung aufheben
        car.setAvailable(true);
        car.setRentedDays(0);
        car.setRentedKilometers(0);
        car.setRentedByCustomer(null);

        // Car-Referenz vom User entfernen
        user.getRentedCars().removeIf(rentedCar -> rentedCar.getId().equals(carId));

        // Änderungen in der Datenbank speichern
        carService.saveCar(car);
        userService.updateUser(user);

        // Zurück zur Übersichtsseite
        return "redirect:/internals/showRentedCarsOverview";
    }
}
