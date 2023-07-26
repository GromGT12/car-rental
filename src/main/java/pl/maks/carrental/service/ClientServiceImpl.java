package pl.maks.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.convertor.ClientConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.SpringDataClientsRepository;
import pl.maks.carrental.repository.model.Client;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    private final SpringDataClientsRepository clientsRepository;
    private final ClientConverter clientConverter;


    public ClientServiceImpl(SpringDataClientsRepository clientsRepository, ClientConverter clientConverter) {
        this.clientsRepository = clientsRepository;

        this.clientConverter = clientConverter;
    }
    @Override
    public List<ClientDTO> getAllClients() {
        Collection<Client> all = clientsRepository.findAll();
        return clientConverter.convertToDto(all);
    }

    @Override
    public ClientDTO getById(Integer id) {
        Client client = clientsRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Client not found" + id));
        return clientConverter.convertToDto(client);
    }

    @Override
    @Transactional
    public Integer createClient(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
