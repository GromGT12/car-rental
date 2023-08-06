package pl.maks.carrental.bankForeignExchange.service.Impl;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.maks.carrental.bankForeignExchange.dto.ForeignExchangeDTO;
import pl.maks.carrental.bankForeignExchange.service.ForeignExchangeService;
import pl.maks.carrental.bankForeignExchange.service.model.ForeignExchange;

import java.util.Arrays;
import java.util.Optional;


@Service
public class ForeignExchangeServiceImpl implements ForeignExchangeService {
    private static final String FOREIGN_EXCHANGE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public ForeignExchangeDTO findForeignExchange() throws ChangeSetPersister.NotFoundException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ForeignExchange[]> response = restTemplate.getForEntity(
                FOREIGN_EXCHANGE,
                ForeignExchange[].class
        );

        ForeignExchange[] resultArray = response.getBody();

        if (resultArray != null && resultArray.length > 0) {
            String currencyCode = "YOUR_DESIRED_CURRENCY_CODE";

            Optional<ForeignExchange> exchangeOptional = Arrays.stream(resultArray)
                    .filter(exchange -> currencyCode.equalsIgnoreCase(exchange.getCc()))
                    .findAny();

            return exchangeOptional.map(exchange -> new ForeignExchangeDTO(exchange.getCc(), exchange.getExchangeDate(), exchange.getR030(), exchange.getRate(), exchange.getTxt()));
        }
        return null;
    }
}