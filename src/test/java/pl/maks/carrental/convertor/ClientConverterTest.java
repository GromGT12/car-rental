package pl.maks.carrental.convertor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.repository.model.Client;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClientConverterTest {
    private static final Integer ID = 12;
    private static final String FIRST_NAME = "TestFirstName";
    private static final String LAST_NAME = "TestLastName";
    private static final String DOCUMENT_NUMBER = "TestDocument_Number";
    private static final Integer ACCIDENTS = 3;

    private final ClientConverter target = new ClientConverter();

    @Test
    @DisplayName("Should convert ClientDTO to Client")
    void shouldConvertClientDtoToEntity() {
        // given
        ClientDTO clientDTO = clientDTO();
        Client expected = entityClient();

        // when
        Client actual = target.convertToEntity(clientDTO);

        // then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getDocumentNumber()).isEqualTo(expected.getDocumentNumber());
        assertThat(actual.getAccidents()).isEqualTo(expected.getAccidents());
    }

    @Test
    @DisplayName("Should convert Client to DTO")
    void shouldConvertClientToDto() {
        // given
        Client client = entityClient();
        ClientDTO expected = clientDTO();

        // when
        ClientDTO actual = target.convertToDto(client);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should convert Collection<Clients> to List<ClientDTO>")
    void shouldConvertCollectionClientsToListClientsDTO() {
        // given
        Collection<Client> clients = List.of(entityClient());
        List<ClientDTO> expected = List.of(clientDTO());

        // when
        List<ClientDTO> actual = target.convertToDto(clients);

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Client entityClient() {
        Client client = new Client();
        client.setId(ID);
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setDocumentNumber(DOCUMENT_NUMBER);
        client.setAccidents(ACCIDENTS);
        return client;
    }

    private ClientDTO clientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(ID);
        clientDTO.setFirstName(FIRST_NAME);
        clientDTO.setLastName(LAST_NAME);
        clientDTO.setDocumentNumber(DOCUMENT_NUMBER);
        clientDTO.setAccidents(ACCIDENTS);
        return clientDTO;
    }
}


