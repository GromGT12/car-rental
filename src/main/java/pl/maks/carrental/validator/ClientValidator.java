package pl.maks.carrental.validator;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Component
public class ClientValidator {
    private static final Pattern ONLY_LETTERS = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern DOCUMENTS_NUMBER = Pattern.compile("\\A(?!\\s*\\Z).+");

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void validateClient(ClientDTO clientDTO) {
        List<String> violations = new ArrayList<>();
        validateLetterField(clientDTO.getFirstName(), "firstName", violations);
        validateLetterField(clientDTO.getLastName(), "lastName", violations);
        validateAccidents(clientDTO, violations);
        validateDocumentNumber(clientDTO, violations);

        if (!violations.isEmpty()) {
            throw new CarRentalValidationException("Provide Client is invalid:", violations);
        }
    }

    private void validateLetterField(String value, String fieldName, List<String> violations) {
        if (isBlank(value)) {
            violations.add(String.format("%s is blank", fieldName));
        }
        if (!ONLY_LETTERS.matcher(value).matches()) {
            violations.add(String.format("%s can contain only letters: '%s'", fieldName, value));
        }
    }

    private void validateLastName(ClientDTO clientDTO, List<String> violations) {
        if (isBlank(clientDTO.getLastName())) {
            violations.add("Last name is blank");
        }
        if (!ONLY_LETTERS.matcher(clientDTO.getLastName()).matches()) {
            violations.add(String.format("%s can contain only digits: '%s'", "LastName", clientDTO.getLastName()));
        }
    }

    void validateDocumentNumber(ClientDTO clientDTO, List<String> violations) {
        if (!DOCUMENTS_NUMBER.matcher(clientDTO.getDocumentNumber()).matches()) {
            violations.add(String.format("invalid documentsNumber: '%s'", clientDTO.getDocumentNumber()));
        }
        List<Client> allByDocumentNumber = clientRepository.findAllByDocumentNumber(clientDTO.getDocumentNumber());
        if (!allByDocumentNumber.isEmpty()) {
            violations.add(String.format("documentsNumber '%s' is already used in the system. Please choose a different one!", clientDTO.getDocumentNumber()));
        }
    }

    private void validateAccidents(ClientDTO clientDTO, List<String> violations) {
        if (clientDTO.getAccidents() == null) {
            violations.add("Accidents count can't be null");
        }

        if (!violations.isEmpty()) {
            throw new CarRentalValidationException("Provide Client is invalid:", violations);
        }
    }
}


