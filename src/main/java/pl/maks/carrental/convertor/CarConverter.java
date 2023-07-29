package pl.maks.carrental.convertor;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.repository.model.Car;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConverter {
    public Car convertToEntity(CarDTO source) {
        return new Car(source.getId(),
                source.getBrand(),
                source.getModel(),
                source.getClassCar(),
                source.getFuel(),
                source.getPricePerDay(),
                source.getParkingId());

    }

    public CarDTO convertToDto(Car source) {
        return convertCarToDto(source);
    }

    public List<CarDTO> convertToDto(Collection<Car> source) {
        return source.stream()
                .map(this::convertCarToDto)
                .collect(Collectors.toList());
    }

    private CarDTO convertCarToDto(Car source) {
        CarDTO result = new CarDTO();
        result.setId(source.getId());
        result.setBrand(source.getBrand());
        result.setModel(source.getModel());
        result.setClassCar(source.getClassCar());
        result.setParkingId(source.getParkingId());
        result.setPricePerDay(source.getPricePerDay());
        result.setFuel(source.getFuel());
        return result;
    }
}

