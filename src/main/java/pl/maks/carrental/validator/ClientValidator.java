package pl.maks.carrental.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Component
public class ClientValidator {

    private static final Pattern ONLY_LETTERS_FIRST_NAME = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern ONLY_LETTERS_LAST_NAME = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern DOCUMENTS_NUMBER = Pattern.compile("\\A(?!\\s*\\Z).+");
    private static final Pattern ACCIDENTS = Pattern.compile("[^0]+");

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void clientValidation(ClientDTO clientDTO) {
        List<String> violations = new ArrayList<>();

        if (isBlank(clientDTO.getFirstName())) {
            violations.add("FirstName is blank");
        }
        if (isBlank(clientDTO.getLastName())) {
            violations.add("LastName is blank");
        }
        if (!ONLY_LETTERS_FIRST_NAME.matcher(clientDTO.getFirstName()).matches()) {
            violations.add(String.format("%s can contain only letters: %s firstname", clientDTO.getFirstName()));
        }
        if (!ONLY_LETTERS_LAST_NAME.matcher(clientDTO.getLastName()).matches()) {
            violations.add(String.format("%s can contain only letters: %s lastname", clientDTO.getLastName()));
        }
        if (!DOCUMENTS_NUMBER.matcher(clientDTO.getDocumentNumber()).matches()) {
            violations.add(String.format("%s can contain only letters: %s documentNumber", clientDTO.getDocumentNumber()));
        }
        if ((clientDTO.getAccidents())==null) {
            violations.add(String.format("%s can contain not null: %s accident", clientDTO.getLastName()));
        }
        List<Client> allByDocumentNumber = clientRepository.findAllByDocumentNumber(clientDTO.getDocumentNumber());
        if (!allByDocumentNumber.isEmpty()) {
            violations.add(String.format("documentNumber already exists in the system, please choose another", clientDTO.getDocumentNumber()));
        }
        if(!violations.isEmpty()){
            String violationMessage = String.join(", ", violations);
            throw new ValidationException("Providate documentNumber is invalide:" + violations);
        }
    }
}


/*
ClientDTO
    - firstName - не пустая строка, только буквы
    - lastName - не пустая строка, только буквы
    - documentNumber - не пустая строка, также сделать проверку на уникальность через БД
    - accidents - не null
    ^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$ номер
 */