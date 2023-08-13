package pl.maks.carrental.service.impl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maks.carrental.exception.ValidationException;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;
import pl.maks.carrental.service.CarRentalService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarRentalServiceIntegrationTest {

    @Autowired
    private CarRentalService carRentalService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testCalculateRentalPrice_WithValidClient() {
        // Arrange
        int clientId = 1;
        int carId = 123;
        int days = 5;
        int accidents = 3;
        BigDecimal expectedPrice = BigDecimal.valueOf(900.0); // 150 * 5 * 1.2

        Client client = new Client();
        client.setId(clientId);
        client.setAccidents(accidents);

        when(clientRepository.getClientInfoWithAccidentsById(clientId)).thenReturn(client);

        // Act
        BigDecimal calculatedPrice = carRentalService.calculateRentalPrice(clientId, carId, days);

        // Assert
        assertEquals(expectedPrice, calculatedPrice);
        verify(clientRepository, times(1)).getClientInfoWithAccidentsById(clientId);
    }

    @Test
    public void testCalculateRentalPrice_WithExcessiveAccidents() {
        // Arrange
        int clientId = 2;
        int carId = 456;
        int days = 3;
        int accidents = 21;

        Client client = new Client();
        client.setId(clientId);
        client.setAccidents(accidents);

        when(clientRepository.getClientInfoWithAccidentsById(clientId)).thenReturn(client);

        // Act & Assert
        assertThrows(ValidationException.class, () -> carRentalService.calculateRentalPrice(clientId, carId, days));
        verify(clientRepository, times(1)).getClientInfoWithAccidentsById(clientId);
    }
}
