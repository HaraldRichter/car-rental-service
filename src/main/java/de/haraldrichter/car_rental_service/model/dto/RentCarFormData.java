package de.haraldrichter.car_rental_service.model.dto;

public class RentCarFormData {
    private CarDTO carDTO;
    private UserDTO userDTO;

    public RentCarFormData(CarDTO carDTO, UserDTO userDTO) {
        this.carDTO = carDTO;
        this.userDTO = userDTO;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
