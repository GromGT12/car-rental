package pl.maks.carrental.bankForeignExchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.bankForeignExchange.dto.ForeignExchangeDTO;
import pl.maks.carrental.bankForeignExchange.service.ForeignExchangeService;

@RestController
@RequestMapping("/currencies")
public class ForeignExchangeController {
    private final ForeignExchangeService foreignExchangeService;

    public ForeignExchangeController(ForeignExchangeService foreignExchangeService) {
        this.foreignExchangeService = foreignExchangeService;
    }

    @GetMapping
    public ForeignExchangeDTO getForeignExchange(@RequestParam String currencyCode) {
        return foreignExchangeService.findForeignExchange(currencyCode);
    }
}

