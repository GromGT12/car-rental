package pl.maks.carrental.service.impl;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.converter.CarConverter;
import pl.maks.carrental.repository.CarRepository;
import pl.maks.carrental.repository.model.Car;
import pl.maks.carrental.service.CarService;
import pl.maks.carrental.validator.CarValidator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarServiceImplTest {
    private final CarRepository carRepository = mock(CarRepository.class);
    private final CarConverter carConverter = mock(CarConverter.class);
    private final CarValidator carValidator = mock(CarValidator.class);
    private final CarService target = new CarServiceImpl(carConverter, carRepository, carValidator);

    @Test
    void shouldCreateCar() {
        //given
        CarDTO carDTO = new CarDTO();
        Car carToSave = new Car();
        Car savedCar = new Car();

        when(carConverter.convertToEntity(carDTO)).thenReturn(carToSave);
        when(carRepository.save(carToSave)).thenReturn(savedCar);

        //when
        Integer actualId = target.createCar(carDTO);

        //then
        verify(carValidator).carValidation(carDTO);
        verify(carConverter).convertToEntity(carDTO);

        assertThat(actualId).isEqualTo(savedCar.getId());
    }

    @Test
    void shouldUpdateCar() {
        // given
        CarDTO updatedCarDTO = new CarDTO();
        Car updatedCar = new Car();
        Car saveCar = new Car();

        when(carConverter.convertToEntity(updatedCarDTO)).thenReturn(updatedCar);
        when(carRepository.findById(anyInt())).thenReturn(Optional.of(saveCar));
        when(carRepository.save(updatedCar)).thenReturn(updatedCar);
        when(carConverter.convertToDto(updatedCar)).thenReturn(updatedCarDTO);

        // when
        CarDTO actualCarDTO = target.updateCar(7, updatedCarDTO);

        // then
        verify(carValidator).carValidation(updatedCarDTO);
        verify(carRepository).findById(7);
        verify(carConverter).convertToEntity(updatedCarDTO);
        verify(carConverter).convertToDto(updatedCar);

        assertThat(actualCarDTO.getId()).isEqualTo(saveCar.getId());
    }

    @Test
    void shouldGetCarById() {
        // given
        Integer carId = 18;
        Car carToReturn = new Car();
        CarDTO saveCarDTO = new CarDTO();

        when(carRepository.findById(carId)).thenReturn(Optional.of(carToReturn));
        when(carConverter.convertToDto(carToReturn)).thenReturn(saveCarDTO);

        // when
        CarDTO actualCarDTO = target.getById(carId);

        // then
        verify(carRepository).findById(carId);
        verify(carConverter).convertToDto(carToReturn);

        assertThat(actualCarDTO).isEqualTo(saveCarDTO);
    }

    @Test
    void shouldGetAllCars() {
        // given
        List<Car> cars = Arrays.asList(new Car(), new Car());
        List<CarDTO> saveCarDTOs = Arrays.asList(new CarDTO(), new CarDTO());

        when(carRepository.findAll()).thenReturn(cars);
        when(carConverter.convertToDto(cars)).thenReturn(saveCarDTOs);

        // when
        List<CarDTO> actualCarDTOs = target.getAllCars();

        // then
        verify(carRepository).findAll();
        verify(carConverter).convertToDto(cars);

        assertThat(actualCarDTOs).isEqualTo(saveCarDTOs);
    }
}
