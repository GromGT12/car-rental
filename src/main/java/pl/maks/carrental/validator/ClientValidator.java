package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ClientDTO;

import java.util.regex.Pattern;

@Component
public class ClientValidator {
    private static final String NAME_REGEX = "^[a-zA-Z]*$";
    private static final String DOCUMENT_NUMBER_REGEX = "^[A-Za-z0-9]{10}$";

    public void validateClient(ClientDTO clientDTO) {
        validateName(clientDTO.getFirstName());
        validateName(clientDTO.getLastName());
        validateDocumentNumber(clientDTO.getDocumentNumber());
    }

    private void validateName(String name) {
        if (Pattern.matches(NAME_REGEX, name)) {
            throw new ValidationException("Invalid name format. Only letters, spaces, hyphens, and apostrophes are allowed.");
        }
    }

    private void validateDocumentNumber(String documentNumber) {
        if (!Pattern.matches(DOCUMENT_NUMBER_REGEX, documentNumber)) {
            throw new ValidationException("Invalid document number format. It should contain 10 characters, including letters and numbers.");
        }
    }
}
