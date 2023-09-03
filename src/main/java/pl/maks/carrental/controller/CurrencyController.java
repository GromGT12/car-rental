package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.controller.productDTO.CurrencyDTO;
import pl.maks.carrental.service.CurrencyService;

@Tag(name = "Exchange management API", description = "API for working with the hryvnia exchange rate")
@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Tag(name = "Exchange Rate", description = "Get foreign exchange rate")
    @GetMapping
    public CurrencyDTO getForeignExchange(@RequestParam String currencyCode) {
        return currencyService.findForeignExchange(currencyCode);
    }
}

