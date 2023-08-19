package pl.maks.carrental.bankForeignExchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.bankForeignExchange.dto.ForeignExchangeDTO;
import pl.maks.carrental.bankForeignExchange.service.ForeignExchangeService;

@Tag(name = "Foreign management API", description = "API for working with the hryvnia exchange rate")

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

