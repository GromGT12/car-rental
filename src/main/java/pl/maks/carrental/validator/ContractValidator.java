package pl.maks.carrental.validator;


import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.exception.CarRentalValidationException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContractValidator {
    public void validateContract(ContractDTO contractDTO) {
        List<String> violations = new ArrayList<>();
        validateCar(contractDTO.getCar(), violations);
        validateClient(contractDTO.getClient(), violations);
        validateDate(contractDTO.getStartDate(), "startDate", violations);
        validateDate(contractDTO.getEndDate(), "endDate", violations);
        validatePrice(contractDTO.getPrice(), violations);

        if (!violations.isEmpty()) {
            String violation = String.join(", ", violations);
            throw new CarRentalValidationException("Provided contract is invalid: ", violations);
        }
    }

    private void validateCar(CarDTO car, List<String> violations) {
        if (car.getId() == null) {
            violations.add("Car id is null");
        }
    }

    private void validateClient(ClientDTO client, List<String> violations) {
        if (client.getId() == null) {
            violations.add("Client id is null");
        }
    }

    private void validateDate(Date date, String fieldName, List<String> violations) {
        if (date == null) {
            violations.add(String.format("%s is null", fieldName));
        }
    }

    private void validatePrice(BigDecimal price, List<String> violations) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            violations.add("Price must not be null and greater than zero");
        }
    }
}

