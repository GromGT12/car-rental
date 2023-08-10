package pl.maks.carrental.convertor;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.repository.model.Client;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ClientConverter {

    public Client convertToEntity(ClientDTO source) {
        return new Client(source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getDocumentNumber(),
                source.getAccidents());

    }

    public ClientDTO convertToDto(Client source) {
        return convertClientToDto(source);
    }

    public List<ClientDTO> convertToDto(Collection<Client> source) {
        return source.stream()
                .map(this::convertClientToDto)
                .collect(toList());
    }

    private ClientDTO convertClientToDto(Client source) {
        ClientDTO result = new ClientDTO();
        result.setId(source.getId());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setDocumentNumber(source.getDocumentNumber());
        result.setAccidents(source.getAccidents());
        return result;
    }
}


