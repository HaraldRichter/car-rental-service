package de.haraldrichter.car_rental_service.service;

import de.haraldrichter.car_rental_service.dto.CarDTO;
import de.haraldrichter.car_rental_service.model.Car;
import de.haraldrichter.car_rental_service.model.InternalCarInfo;
import de.haraldrichter.car_rental_service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Create a Car-Object from a CarDTO-"Blueprint"
     * @param carDTO the CarDTO-Object to be turned into an actual Car-Object.
     * @return the Car-Object with the field values provided by the CarDTO-Blueprint.
     */
    public Car mapCarDTOToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setPriceCategory(carDTO.getPriceCategory());
        car.setType(carDTO.getType());
        car.setManufacturer(carDTO.getManufacturer());
        car.setModel(carDTO.getModel());
        car.setDescription(carDTO.getDescription());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setFuelType(carDTO.getFuelType());
        car.setBasePrice(carDTO.getBasePrice());
        car.setKilometerPrice(carDTO.getKilometerPrice());
        car.setRentedStatus(carDTO.isRentedStatus());
        car.setRentedDays(carDTO.getRentedDays());
        car.setRentedKilometers(carDTO.getRentedKilometers());

        if (carDTO.getInternalCarInfo() != null) {
            System.out.println("InternalCarInfo is not null and being set.");
            InternalCarInfo internalCarInfo = new InternalCarInfo();
            internalCarInfo.setLicensePlate(carDTO.getInternalCarInfo().getLicensePlate());
            internalCarInfo.setMileage(carDTO.getInternalCarInfo().getMileage());
            internalCarInfo.setNextInspection(carDTO.getInternalCarInfo().getNextInspection());
            internalCarInfo.setNotes(carDTO.getInternalCarInfo().getNotes());
            car.setInternalCarInfo(internalCarInfo);
        } else {
            System.out.println("InternalCarInfo is null.");
        }

        return car;
    }

    @Override
    public String createCar(CarDTO carDTO) {
        Car car = mapCarDTOToCar(carDTO);
        carRepository.save(car);
        return "Car added successfully.";
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(String id) {
        // Try to get the data from the database; Optional in case the query fails
        Optional<Car> result = carRepository.findById(id);

        // Check if we got data back; if not, throw error
        Car car = null;
        if (result.isPresent()) {
            car = result.get();
        }
        else {
            // we didn't find the car
            throw new RuntimeException("This car doesn't exist in the database! Invalid id: " + id);
        }

        return car;
    }


}


//    public CarDTO makeCar() {
//        CarDTO carDTO = new CarDTO();
//        InternalCarInfo info = new InternalCarInfo("A-OI 1349", 666, "24.09.2025", "Everything's fine.");
//        carDTO.setPriceCategory("Standard");
//        carDTO.setSizeCategory("Standard");
//        carDTO.setType("Sedan");
//        carDTO.setManufacturer("Toyota");
//        carDTO.setModel("Corolla");
//        carDTO.setDescription("Great Car!");
//        carDTO.setTransmissionType("Manual");
//        carDTO.setFuelType("Petrol");
//        carDTO.setBasePrice(29.99);
//        carDTO.setKilometerPrice(0.69);
//        carDTO.setRentedStatus(false);
//        carDTO.setRentedDays(0);
//        carDTO.setRentedKilometers(0);
//        carDTO.setInternalCarInfos(info);
//
//        return carDTO;
//    }