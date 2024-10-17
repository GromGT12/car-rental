package pl.maks.carrental.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import pl.maks.carrental.repository.ClientRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ClientValidatorTest {

    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientValidator target = new ClientValidator(clientRepository);

    @Test
    @DisplayName("Validation Error should not be thrown when input client is valid")
    void shouldNotThrow_whenClientIsValid() {
        //given
        ClientDTO valid = validClient();

        //when
        assertDoesNotThrow(() -> target.validateClient(valid));

        //then
    }

    @Test
    @DisplayName("Validation Error should be thrown when accidents count is null")
    void shouldThrow_whenAccidentsCountIsNull() {
        //given
        ClientDTO invalidClient = invalidClient();

        //when
        CarRentalValidationException carRentalValidationException = assertThrows(CarRentalValidationException.class, () -> target.validateClient(invalidClient));

        //then
        assertThat(carRentalValidationException.getViolations()).contains("Accidents count can't be null");
    }

    private ClientDTO validClient() {
        ClientDTO dto = new ClientDTO();
        dto.setAccidents(11);
        dto.setDocumentNumber("dxz875848382");
        dto.setFirstName("TestFirstName");
        dto.setLastName("TestLastName");
        return dto;
    }

    private ClientDTO invalidClient() {
        ClientDTO dto = new ClientDTO();
        dto.setDocumentNumber("dxz875848382");
        dto.setFirstName("TestFirstName");
        dto.setLastName("TestLastName");
        dto.setAccidents(null);
        return dto;
    }
}
