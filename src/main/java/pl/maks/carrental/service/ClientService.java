package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getById(Integer id);

    Integer createClient(ClientDTO clientDTO);

    void deleteById(Integer id);

    ClientDTO updateClient(Integer id, ClientDTO clientToUpdate);
}

