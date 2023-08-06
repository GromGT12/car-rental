package pl.maks.carrental.bankForeignExchange.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import pl.maks.carrental.bankForeignExchange.dto.ForeignExchangeDTO;

public interface ForeignExchangeService {
    public ForeignExchangeDTO findForeignExchange() throws ChangeSetPersister.NotFoundException;


    ForeignExchangeDTO findForeignExchang();
}
