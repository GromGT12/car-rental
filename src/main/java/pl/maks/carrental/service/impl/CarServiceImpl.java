package pl.maks.carrental.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.convertor.CarConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.CarRepository;
import pl.maks.carrental.repository.model.Car;
import pl.maks.carrental.service.CarService;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarConverter carConverter;
    private final CarRepository carRepository;

    public CarServiceImpl(CarConverter carConverter, CarRepository carRepository) {
        this.carConverter = carConverter;
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDTO> getAllCars() {
        Collection<Car> all = carRepository.findAll();
        return carConverter.convertToDto(all);
    }

    @Override
    public CarDTO getById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Car not found" + id));
        return carConverter.convertToDto(car);
    }

    @Override
    @Transactional
    public Integer createCar(CarDTO carToCreate) {
        Car car = carConverter.convertToEntity(carToCreate);
        Car saveCar = carRepository.save(car);
        return saveCar.getId();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found" + id));
        carRepository.delete(car);

    }

    @Override
    public CarDTO updateCar(Integer id, CarDTO carToUpdate) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Client not found" + id));
        Car entityToUpdate = carConverter.convertToEntity(carToUpdate);
        entityToUpdate.setId(id);
        Car updateEntity = carRepository.save(entityToUpdate);
        return carConverter.convertToDto(updateEntity);
    }
}

