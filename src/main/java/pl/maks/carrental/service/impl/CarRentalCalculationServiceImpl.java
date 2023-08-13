package pl.maks.carrental.service.impl;

import org.springframework.stereotype.Service;
import pl.maks.carrental.exception.ValidationException;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;
import pl.maks.carrental.service.CarRentalService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class CarRentalCalculationServiceImpl implements CarRentalService {

    private final BigDecimal BASE_DAILY_RATE = BigDecimal.valueOf(150.0);
    private final BigDecimal ACCIDENT_COEFFICIENT_1 = BigDecimal.valueOf(1.0);
    private final BigDecimal ACCIDENT_COEFFICIENT_2 = BigDecimal.valueOf(1.2);
    private final BigDecimal ACCIDENT_COEFFICIENT_3 = BigDecimal.valueOf(1.5);

    private final ClientRepository clientRepository;

    public CarRentalCalculationServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public BigDecimal calculateRentalPrice(Integer client, Integer car, Integer days) {
        int accidents = getAccidents(client);

        int MAX_ACCIDENTS = 20;
        if (accidents >= MAX_ACCIDENTS) {
            List<String> violationMessage = Collections.singletonList("Validation failed");
            throw new ValidationException("Client has 20 accidents and can't rent a car in our service", violationMessage);
        }

        BigDecimal coefficient = getAccidentCoefficient(accidents);
        return BASE_DAILY_RATE.multiply(BigDecimal.valueOf(days)).multiply(coefficient);
    }

    private int getAccidents(Integer clientId) {
        Client clientDTO = clientRepository.getClientInfoWithAccidentsById(clientId);
        return clientDTO.getAccidents();
    }

    private BigDecimal getAccidentCoefficient(int accidents) {
        if (accidents <= 5) {
            return ACCIDENT_COEFFICIENT_1;
        } else if (accidents <= 10) {
            return ACCIDENT_COEFFICIENT_2;
        } else {
            return ACCIDENT_COEFFICIENT_3;
        }
    }
}
