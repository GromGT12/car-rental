package pl.maks.carrental.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.maks.carrental.controller.productDTO.CurrencyDTO;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.service.CurrencyService;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String FOREIGN_EXCHANGE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public CurrencyDTO findForeignExchange(String CurrencyDTOCode) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrencyDTO[]> response = restTemplate.getForEntity(
                FOREIGN_EXCHANGE,
                CurrencyDTO[].class
        );

        CurrencyDTO[] resultArray = response.getBody();

        if (resultArray != null && resultArray.length > 0) {
            Optional<CurrencyDTO> exchangeOptional = Arrays.stream(resultArray)
                    .filter(exchange -> CurrencyDTOCode.equalsIgnoreCase(exchange.getCc()))
                    .findAny();
            return exchangeOptional
                    .orElseThrow(() -> new CarRentalNotFoundException(String.format("CurrencyDTO not found: %s", CurrencyDTOCode)));
        } else {
            throw new CarRentalNotFoundException(String.format("CurrencyDTO not found: %s", CurrencyDTOCode));
        }
    }
}
