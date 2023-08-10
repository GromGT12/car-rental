package pl.maks.carrental.convertor;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.repository.model.Contract;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ContractConverter {
    private final CarConverter carConverter;
    private final ClientConverter clientConverter;


    public ContractConverter(CarConverter carConverter, ClientConverter clientConverter) {
        this.carConverter = carConverter;
        this.clientConverter = clientConverter;
    }

    public ContractDTO convertToDto(Contract contract) {
        return convertContract(contract);
    }

    public List<ContractDTO> convertToDto(Collection<Contract> source) {
        return source.stream()
                .map(this::convertToDto)
                .collect(toList());
    }

    public Contract convertToEntity(ContractDTO source) {
        Contract result = new Contract();
        result.setId(source.getId());
        result.setCar(carConverter.convertToEntity(source.getCar()));
        result.setClient(clientConverter.convertToEntity(source.getClient()));
        result.setEndDate(source.getEndDate());
        result.setStartDate(source.getStartDate());
        result.setPrice(source.getPrice());
        return result;
    }

    private ContractDTO convertContract(Contract source) {
        ContractDTO result = new ContractDTO();
        result.setId(source.getId());
        result.setCar(carConverter.convertToDto(source.getCar()));
        result.setClient(clientConverter.convertToDto(source.getClient()));
        result.setEndDate(source.getEndDate());
        result.setStartDate(source.getStartDate());
        result.setPrice(source.getPrice());
        return result;
    }
}