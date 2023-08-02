package pl.maks.carrental.controller;

import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public CarDTO getById(@PathVariable Integer id) {
        return carService.getById(id);
    }

    @GetMapping
    public List<CarDTO> getAll() {
        return carService.getAllCars();
    }

    @PostMapping
    public Integer CreateCar(@RequestBody CarDTO carToCreate) {
        return carService.createCar(carToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        carService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CarDTO update(@PathVariable Integer id, @RequestBody CarDTO carToUpdate) {
        return carService.updateCar(id, carToUpdate);
    }
}

