package pl.maks.carrental.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarRentalCalculationServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @Test
    void testCalculateRentalPrice() {
        // given
        Integer clientId = 1;
        Integer carId = 1;
        Integer days = 5;
        Integer accidents = 3;

        Client client = new Client();
        client.setAccidents(accidents);
        when(clientRepository.getReferenceById(clientId)).thenReturn(client);

        CarRentalCalculationServiceImplService calculationService = new CarRentalCalculationServiceImplService(clientRepository);

        // when
        BigDecimal rentalPrice = calculationService.calculateRentalPrice(clientId, carId, days);

        // then
        BigDecimal expectedPrice = BigDecimal.valueOf(150.0).multiply(BigDecimal.valueOf(days)).multiply(BigDecimal.valueOf(1.0));
        assertThat(rentalPrice).isEqualTo(expectedPrice);
    }
}
