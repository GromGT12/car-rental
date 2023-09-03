package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.controller.productDTO.CurrencyDTO;
import pl.maks.carrental.service.CurrencyService;
import pl.maks.carrental.service.RentalPriceCalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "CarRentalController management API", description = "API for operation with cars")
@RestController
public class PriceCalculationController {

    private final RentalPriceCalculatorService rentalPriceCalculatorService;
    private final CurrencyService currencyService;

    @Autowired
    public PriceCalculationController(RentalPriceCalculatorService rentalPriceCalculatorService, CurrencyService currencyService) {
        this.rentalPriceCalculatorService = rentalPriceCalculatorService;
        this.currencyService = currencyService;
    }

    @Tag(name = "Calculating the rental cost of a car", description = "In case the client exceeds the accident limit, they will be denied the car rental")
    @GetMapping("/calculate-rental-price")
    public String calculateRentalPrice(
            @RequestParam Integer client,
            @RequestParam Integer car,
            @RequestParam Integer days) {

        BigDecimal totalPriceInPln = rentalPriceCalculatorService.calculateRentalPrice(client, car, days);
        CurrencyDTO currencyDTO = currencyService.findForeignExchange("PLN");
        BigDecimal exchangeRate = currencyDTO.getRate();
        BigDecimal totalPriceInUsd = totalPriceInPln.divide(exchangeRate, 2, RoundingMode.HALF_UP);
        return String.format("%.2f PLN -> %.2f USD", totalPriceInPln, totalPriceInUsd);
    }
}


