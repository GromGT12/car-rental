package pl.maks.carrental.validator;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import pl.maks.carrental.repository.CarRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Component
public class CarValidator {

    private static final Pattern ONLY_LETTERS = Pattern.compile("^[a-zA-Z]*$");
    private final CarRepository carRepository;

    public CarValidator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void carValidation(CarDTO carDTO) {
        List<String> violations = new ArrayList<>();

        validateLetterField(carDTO.getBrand(), "Brand", violations);
        validateLetterField(carDTO.getModel(), "Model", violations);
        validateLetterField(carDTO.getCarClass(), "CarClass", violations);
        validateLetterField(carDTO.getFuel(), "Fuel", violations);
        validatePrice(carDTO.getPricePerDay(), "PricePerDay", violations);

        if (!violations.isEmpty()) {
            String violation = String.join(", ", violations);
            throw new CarRentalValidationException("Provided car details are invalid: ", violations);
        }
    }

    private void validateLetterField(String value, String fieldName, List<String> violations) {
        if (isBlank(value)) {
            violations.add(fieldName + " is blank");
        }
        if (!ONLY_LETTERS.matcher(value).matches()) {
            violations.add(fieldName + " can contain only letters: " + value);
        }
    }

    private void validatePrice(BigDecimal price, String fieldName, List<String> violations) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            violations.add(fieldName + " must not be null and greater than zero");
        }
    }

    private void validateParking(CarDTO carDTO, List<String> violations) {
        if (carDTO == null || carDTO.getId() == null) {
            violations.add("Parking id is null");
        }
    }
}


