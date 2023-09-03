package pl.maks.carrental.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.maks.carrental.controller.productDTO.CurrencyDTO;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.model.Currency;
import pl.maks.carrental.service.CurrencyService;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String FOREIGN_EXCHANGE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public CurrencyDTO findForeignExchange(String currencyCode) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Currency[]> response = restTemplate.getForEntity(
                FOREIGN_EXCHANGE,
                Currency[].class
        );

        Currency[] resultArray = response.getBody();

        if (resultArray != null && resultArray.length > 0) {

            Optional<Currency> exchangeOptional = Arrays.stream(resultArray)
                    .filter(exchange -> currencyCode.equalsIgnoreCase(exchange.getCc()))
                    .findAny();

            return exchangeOptional.map(exchange -> new CurrencyDTO(
                    exchange.getR030(),
                    exchange.getTxt(),
                    exchange.getRate(),
                    exchange.getCc(),
                    exchange.getExchangeDate()
            )).orElseThrow(() -> new CarRentalNotFoundException(String.format("Currency not found: %s", currencyCode)));
        } else {
            throw new CarRentalNotFoundException(String.format("Currency not found: %s", currencyCode));
        }
    }
}
