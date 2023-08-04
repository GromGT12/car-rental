package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.repository.ParkingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Component
public class ParkingValidator {
    private static final Pattern ONLY_LETTERS_NAME = Pattern.compile("^[a-zA-Z0-9\\s'-]*$");
    private static final Pattern PHONE_NUMBER = Pattern.compile("^(?:\\+?\\d{1,4}\\s?)?(?:\\(\\d{1,4}\\)\\s?)?(?:[-.\\s]?\\d{1,5}){1,6}$");

    private final ParkingRepository parkingRepository;

    public ParkingValidator(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public void parkingValidation(ParkingDTO parkingDTO) {
        List<String> violations = new ArrayList<>();

        if (isBlank(parkingDTO.getName())) {
            violations.add("Name is blank");
        }
        if (isBlank(parkingDTO.getPhone())) {
            violations.add("Phone is blank");
        }
        if (!ONLY_LETTERS_NAME.matcher(parkingDTO.getName()).matches()) {
            violations.add(String.format("%s can contain only letters: %s name", parkingDTO.getName()));
        }
        if (!PHONE_NUMBER.matcher(parkingDTO.getPhone()).matches()) {
            violations.add(String.format("Invalid phone number: %s", parkingDTO.getPhone()));
        }

        if (!violations.isEmpty()) {
            String violationMessage = String.join(", ", violations);
            throw new ValidationException("Provided parking details are invalid: " + violationMessage);
        }
    }
}