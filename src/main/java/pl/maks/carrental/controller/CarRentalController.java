package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maks.carrental.service.CarRentalService;

import java.math.BigDecimal;

@Tag(name = "CarRentalController management API", description = "API for CRUD operation with cars")
@RestController
public class CarRentalController {

    private final CarRentalService carRentalService;

    @Autowired
    public CarRentalController(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }

    @Tag(name = "Calculating the rental cost of a car", description = "In case the client exceeds the accident limit, they will be denied the car rental")
    @GetMapping("/calculate-Rental-Price")
    public ResponseEntity<String> calculateRentalPrice(
            @RequestParam Integer client,
            @RequestParam Integer car,
            @RequestParam Integer days) {

        try {
            BigDecimal rentalPrice = carRentalService.calculateRentalPrice(client, car, days);
            return ResponseEntity.ok("Rental price: " + rentalPrice.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
