package pl.maks.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.convertor.CarConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.SpringDataCarsRepository;
import pl.maks.carrental.repository.model.Car;
import pl.maks.carrental.validator.CarValidator;

import java.util.Collection;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final SpringDataCarsRepository carsRepository;
    private final CarConverter carConverter;
    private final CarValidator carValidator;

    public CarServiceImpl(SpringDataCarsRepository carsRepository, CarConverter carConverter, CarValidator carValidator) {
        this.carsRepository = carsRepository;
        this.carConverter = carConverter;
        this.carValidator = carValidator;
    }

    @Override
    public List<CarDTO> getAllCars() {
        Collection<Car> all = carsRepository.findAll();
        return carConverter.convertToDto(all);
    }

    @Override
    public CarDTO getById(Integer id) {
        Car car = carsRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Car not found" + id));
        return carConverter.convertToDto(car);
    }

    @Override
    @Transactional
    public Integer createCar(CarDTO carToCreate) {
        carValidator.validateCar(carToCreate);
        Car car = carConverter.convertToEntity(carToCreate);
        Car saveCar = carsRepository.save(car);
        return saveCar.getId();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Car car = carsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found" + id));
        carsRepository.delete(car);

    }

    @Override
    @Transactional
    public CarDTO updateCar(Integer id, CarDTO carToUpdate) {
        Car car = carsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found" + id));
        Car entityToUpdate = carConverter.convertToEntity(carToUpdate);
        entityToUpdate.setId(id);
        Car updateEntity = carsRepository.save(car);
        return carConverter.convertToDto(updateEntity);
    }
}
