package pl.maks.carrental.service.impl;

import org.springframework.stereotype.Service;
import pl.maks.carrental.exception.CarRentalValidationException;
import pl.maks.carrental.repository.ClientRepository;
import pl.maks.carrental.repository.model.Client;
import pl.maks.carrental.service.RentalPriceCalculatorService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class CarRentalCalculationServiceImpl implements RentalPriceCalculatorService {

    private static final BigDecimal BASE_DAILY_RATE = BigDecimal.valueOf(150.0);
    private static final BigDecimal ACCIDENT_COEFFICIENT_1 = BigDecimal.valueOf(1.0);
    private static final BigDecimal ACCIDENT_COEFFICIENT_2 = BigDecimal.valueOf(1.2);
    private static final BigDecimal ACCIDENT_COEFFICIENT_3 = BigDecimal.valueOf(1.5);

    private final ClientRepository clientRepository;

    public CarRentalCalculationServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public BigDecimal calculateRentalPrice(Integer client, Integer car, Integer days) {
        int accidents = getAccidents(client);
        int maxAccident = 20;
        if (accidents >= maxAccident) {
            List<String> violationMessage = Collections.singletonList("Client has " + accidents + " accidents and can't rent a car in our service");
            throw new CarRentalValidationException("Validation failed", violationMessage);

        }

        BigDecimal coefficient = getAccidentCoefficient(accidents);
        return BASE_DAILY_RATE.multiply(BigDecimal.valueOf(days)).multiply(coefficient);
    }

    private int getAccidents(Integer clientId) {
        Client clientDTO = clientRepository.getReferenceById(clientId);
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

