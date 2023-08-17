package pl.maks.carrental.service;

import java.math.BigDecimal;

public interface RentalPriceCalculatorService {
    BigDecimal calculateRentalPrice(Integer client, Integer car, Integer days);
}


