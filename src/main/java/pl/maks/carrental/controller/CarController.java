package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.service.CarService;

import java.util.List;

@Tag(name = "Car management API", description = "API for operation with cars")
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Tag(name = "Fetching a car by ID", description = "In case the car is not found, the response will have a status of 404")
    @GetMapping("/{id}")
    public CarDTO getById(@PathVariable Integer id) {
        return carService.getById(id);
    }

    @Tag(name = "Retrieving all cars", description = "In case no car is found, the response will have a status of 404")
    @GetMapping
    public List<CarDTO> getAll() {
        return carService.getAllCars();
    }

    @Tag(name = "Creating a new car", description = "In case the car is not created, the response will have a status of 404")
    @PostMapping
    public Integer carCreate(@RequestBody CarDTO carToCreate) {
        return carService.createCar(carToCreate);
    }

    @Tag(name = "Delete a car by ID", description = "In case the car is not deleted by ID, the response will have a status of 404")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        carService.deleteById(id);
    }

    @Tag(name = "Updating a car by ID", description = "In case the car is not updated correctly by ID, the response will have a status of 404")
    @PutMapping("/{id}")
    public CarDTO update(@PathVariable Integer id, @RequestBody CarDTO carToUpdate) {
        return carService.updateCar(id, carToUpdate);
    }
}

