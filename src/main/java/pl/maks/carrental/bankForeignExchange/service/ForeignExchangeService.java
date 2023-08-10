package pl.maks.carrental.bankForeignExchange.service;

import pl.maks.carrental.bankForeignExchange.dto.ForeignExchangeDTO;

public interface ForeignExchangeService {
    ForeignExchangeDTO findForeignExchange(String currencyCode);
}