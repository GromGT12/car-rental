package pl.maks.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.convertor.ClientConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.SpringDataClientsRepository;
import pl.maks.carrental.repository.model.Client;
import pl.maks.carrental.validator.ClientValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
    private final SpringDataClientsRepository clientsRepository;
    private final ClientConverter clientConverter;
    private final ClientValidator clientValidator;


    public ClientServiceImpl(SpringDataClientsRepository clientsRepository, ClientConverter clientConverter, ClientValidator clientValidator) {
        this.clientsRepository = clientsRepository;
        this.clientConverter = clientConverter;
        this.clientValidator = clientValidator;
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
    public Integer createClient(ClientDTO clientToCreate) {
        clientValidator.validateClient(clientToCreate);
        Client client = clientConverter.convertToEntity(clientToCreate);
        Client saveClient = clientsRepository.save(client);
        return saveClient.getId();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Client client = clientsRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Client not found" + id));
        clientsRepository.delete(client);

    }

    @Override
    @Transactional
    public ClientDTO updateClient(Integer id, ClientDTO clientToUpdate) {
        Client client = clientsRepository.findById(id).orElseThrow(()-> new CarRentalNotFoundException("Client not found"+ id));
        Client entityToUpdate = clientConverter.convertToEntity(clientToUpdate);
        entityToUpdate.setId(id);
        Client updateEntity = clientsRepository.save(entityToUpdate);
        return clientConverter.convertToDto(updateEntity);
    }
}
