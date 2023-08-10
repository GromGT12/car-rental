package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Component
public class CarValidator {

    private static final Pattern ONLY_LETTERS = Pattern.compile("^[a-zA-Z]*$");

    public void carValidation(CarDTO carDTO) {
        List<String> violations = new ArrayList<>();

        validateLetterField(carDTO.getBrand(), "Brand", violations);
        validateLetterField(carDTO.getModel(), "Model", violations);
        validateLetterField(carDTO.getCarClass(), "CarClass", violations);
        validateLetterField(carDTO.getFuel(), "Fuel", violations);
        validatePrice(carDTO.getPricePerDay(), violations);

        if (!violations.isEmpty()) {
            String violationMessage = String.join(", ", violations);
            throw new ValidationException("Provided car details are invalid: " + violationMessage);
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

    private void validatePrice(BigDecimal price, List<String> violations) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            violations.add("PricePerDay" + " must not be null and greater than zero");
        }
    }

    private void validateParking(CarDTO carDTO, List<String> violations) {
        if (carDTO == null || carDTO.getId() == null) {
            violations.add("Parking id is null");
        }
    }
}


