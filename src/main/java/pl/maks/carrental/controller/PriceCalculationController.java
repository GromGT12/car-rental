package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.service.RentalPriceCalculatorService;

import java.math.BigDecimal;

@Tag(name = "CarRentalController management API", description = "API for operation with cars")
@RestController
public class PriceCalculationController {

    private final RentalPriceCalculatorService rentalPriceCalculatorService;

    @Autowired
    public PriceCalculationController(RentalPriceCalculatorService rentalPriceCalculatorService) {
        this.rentalPriceCalculatorService = rentalPriceCalculatorService;
    }

    @Tag(name = "Calculating the rental cost of a car", description = "In case the client exceeds the accident limit, they will be denied the car rental")
    @GetMapping("/calculate-rental-price")
    public BigDecimal calculateRentalPrice(
            @RequestParam Integer client,
            @RequestParam Integer car,
            @RequestParam Integer days) {

        return rentalPriceCalculatorService.calculateRentalPrice(client, car, days);
    }
}


