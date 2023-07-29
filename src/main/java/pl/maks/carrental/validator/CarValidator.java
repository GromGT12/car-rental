package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;

@Component
public class CarValidator {

    public void validateCar(CarDTO carDTO) {
        if (!carDTO.getClassCar().endsWith("class car")) {
            throw new ValidationException("Car class should be on class car");
        }
    }
}

