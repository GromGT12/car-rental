package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();

    CarDTO getById(Integer id);

    Integer createCar(CarDTO carDTO);

    void deleteById(Integer id);

    CarDTO updateCar(Integer id, CarDTO carToUpdate);
}
