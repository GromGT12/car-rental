package pl.maks.carrental.convertor;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.repository.model.Car;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConverter {
    private final ParkingConverter parkingConverter;

    public CarConverter(ParkingConverter parkingConverter) {
        this.parkingConverter = parkingConverter;
    }

    public CarDTO convertToDto(Car car) {
        return convertCar(car);
    }

    public List<CarDTO> convertToDto(Collection<Car> source) {
        return source.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Car convertToEntity(CarDTO source) {
        Car result = new Car();
        result.setId(source.getId());
        result.setBrand(source.getBrand());
        result.setCarClass(source.getCarClass());
        result.setFuel(source.getFuel());
        result.setModel(source.getModel());
        result.setPricePerDay(source.getPricePerDay());
        result.setParking(parkingConverter.convertToEntity(source.getParking()));
        return result;
    }

    private CarDTO convertCar(Car source) {
        CarDTO result = new CarDTO();
        result.setId(source.getId());
        result.setBrand(source.getBrand());
        result.setCarClass(source.getCarClass());
        result.setFuel(source.getFuel());
        result.setModel(source.getModel());
        result.setPricePerDay(source.getPricePerDay());
        return result;
    }
}


