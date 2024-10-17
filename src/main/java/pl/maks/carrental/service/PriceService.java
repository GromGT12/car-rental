
package pl.maks.carrental.service;

import java.math.BigDecimal;

public interface PriceService {
    BigDecimal calculateRentalPrice(Integer client, Integer car, Integer days);
}
