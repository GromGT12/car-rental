package pl.maks.carrental;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.maks.carrental.controller.productDTO.ClientDTO;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Testcontainers
@SpringBootTest(classes = CarRentalApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientLifecycleIT {

    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getPassword);
        registry.add("spring.datasource.password", postgres::getUsername);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @Test
    @DisplayName("Check if the clients were created when the program was started")
    void checkClients() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<ClientDTO[]> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients", ClientDTO[].class);
        ClientDTO[] body = forEntity.getBody();

//        assertThat(body).isNotEmpty();
    }

    @Test
    void verifyClientLifecycle() {
        // given
        TestRestTemplate restTemplate = new TestRestTemplate();
        ClientDTO anotherClient = anotherClient();
        ClientDTO updateClient = updateClient();
        String updatedClientFirstName = updateClient.getFirstName();
        String updatedClientLastName = updateClient.getLastName();
        String updatedClientDocumentNumber = updateClient.getDocumentNumber();
        Integer updatedClientAccident = updateClient.getAccidents();

        // prepare request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientDTO> request = new HttpEntity<>(anotherClient, headers);
        HttpEntity<ClientDTO> requestUpdate = new HttpEntity<>(updateClient, headers);

        // security
        String auth = "admin" + ":" + "encodedPassword";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);

        // client creation
        ResponseEntity<Integer> createResponse = restTemplate.postForEntity("http://localhost:" + port + "/clients", request, Integer.class);
        Integer createdClientId = createResponse.getBody();

        // when
        ClientDTO actualClient = restTemplate.getForObject("http://localhost:" + port + "/clients/" + createdClientId, ClientDTO.class);

        // update client
        updateClient.setId(createdClientId);
        ResponseEntity<ClientDTO> updatedClient = restTemplate.exchange("http://localhost:" + port + "/clients/" + createdClientId, HttpMethod.PUT, requestUpdate, ClientDTO.class);
        ClientDTO updatedClientBody = updatedClient.getBody();


        // delete client
        restTemplate.delete("http://localhost:" + port + "/clients/" + createdClientId);

        // then
        HttpClientErrorException.NotFound actualException = assertThrows(HttpClientErrorException.NotFound.class,
                () -> restTemplate.getForObject("http://localhost:" + port + "/clients/" + createdClientId, ClientDTO.class));

        String expectedMessage = String.format("404 : \"Client not found: %d\"", createdClientId);

        //create client then
        assertThat(actualClient).isNotNull();
        assertThat(actualClient.getFirstName()).isEqualTo(anotherClient.getFirstName());
        assertThat(actualClient.getLastName()).isEqualTo(anotherClient().getLastName());
        assertThat(actualClient.getDocumentNumber()).isEqualTo(anotherClient.getDocumentNumber());
        assertThat(actualClient.getAccidents()).isEqualTo(anotherClient.getAccidents());

        //update client then
        assert updatedClientBody != null;
        assertThat(updatedClientBody.getFirstName()).isEqualTo(updatedClientFirstName);
        assertThat(updatedClientBody.getLastName()).isEqualTo(updatedClientLastName);
        assertThat(updatedClientBody.getDocumentNumber()).isEqualTo(updatedClientDocumentNumber);
        assertThat(updatedClientBody.getAccidents()).isEqualTo(updatedClientAccident);

        //delete client then
        assertThat(actualException.getMessage()).isEqualTo(expectedMessage);
    }

    private ClientDTO anotherClient() {
        ClientDTO client = new ClientDTO();
        client.setFirstName("FirstName");
        client.setLastName("LastName");
        client.setDocumentNumber("zkq87898789");
        client.setAccidents(1);
        return client;
    }

    private ClientDTO updateClient() {
        ClientDTO client = new ClientDTO();
        client.setFirstName("FirstName");
        client.setLastName("LastName");
        client.setDocumentNumber("zkq87898789");
        client.setAccidents(1);
        return client;
    }
}
