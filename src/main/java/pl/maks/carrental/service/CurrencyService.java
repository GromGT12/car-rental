package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.CurrencyDTO;

public interface CurrencyService {
    CurrencyDTO findForeignExchange(String currencyCode);
}