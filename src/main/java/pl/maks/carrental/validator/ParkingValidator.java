package pl.maks.carrental.validator;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.exception.CarRentalValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;


@Component
public class ParkingValidator {
    private static final Pattern ONLY_LETTERS_NAME = Pattern.compile("^[a-zA-Z0-9\\s'-]*$");
    private static final Pattern PHONE_NUMBER = Pattern.compile("^(?:\\+?\\d{1,4}\\s?)?(?:\\(\\d{1,4}\\)\\s?)?(?:[-.\\s]?\\d{1,5}){1,6}$");


    public void parkingValidation(ParkingDTO parkingDTO) {
        List<String> violations = new ArrayList<>();
        validateLetterField(parkingDTO.getName(), "name", violations);
        validateLetterField(parkingDTO.getPhone(), "phone", violations);
        validatePhone(parkingDTO.getPhone(), violations);

        if (!violations.isEmpty()) {
            throw new CarRentalValidationException("Provide Car is invalid:", violations);
        }

    }

    private void validateLetterField(String value, String fieldName, List<String> violations) {
        if (isBlank(value)) {
            violations.add(String.format("%s is blank", fieldName));
        }
        if (!ONLY_LETTERS_NAME.matcher(value).matches()) {
            violations.add(String.format("%s can contain only letters: %s name", fieldName, value));
        }
    }

    private void validatePhone(String phoneNumber, List<String> violations) {
        if (isBlank(phoneNumber)) {
            violations.add("Phone is blank");
        }
        if (!PHONE_NUMBER.matcher(phoneNumber).matches()) {
            violations.add(String.format("%s can contain only digits: '%s'", "phone", phoneNumber));
        }
    }
}