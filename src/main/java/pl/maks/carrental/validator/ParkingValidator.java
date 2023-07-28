package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ParkingDTO;

import java.util.regex.Pattern;

@Component
public class ParkingValidator {
    private static final String NAME_REGEX = "^[a-zA-Z0-9\\s'-]*$";
    private static final String PHONE_NUMBER_REGEX = "^(?:\\+?\\d{1,4}\\s?)?(?:\\(\\d{1,4}\\)\\s?)?(?:[-.\\s]?\\d{1,5}){1,6}$";

    public void validateParking(ParkingDTO parking) {
        validateName(parking.getName());
        validatePhoneNumber(parking.getPhone());
    }

    private void validateName(String name) {
        if (!Pattern.matches(NAME_REGEX, name)) {
            throw new ValidationException("Invalid parking name format. Only letters, digits, spaces, hyphens, and apostrophes are allowed.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber)) {
            throw new ValidationException("Invalid parking phone number format. Please provide a valid phone number.");
        }
    }
}

