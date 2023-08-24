package pl.maks.carrental.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.convertor.ContractConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.ContractRepository;
import pl.maks.carrental.repository.model.Contract;
import pl.maks.carrental.service.ContractService;
import pl.maks.carrental.validator.ContractValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContractServiceImpl implements ContractService {

    private final ContractConverter contractConverter;
    private final ContractRepository contractRepository;
    private final ContractValidator contractValidator;
    private static final String CONTRACT_NOT_FOUND_MESSAGE = "Contract not found";


    public ContractServiceImpl(ContractConverter contractConverter, ContractRepository contractRepository, ContractValidator contractValidator) {
        this.contractConverter = contractConverter;
        this.contractRepository = contractRepository;
        this.contractValidator = contractValidator;
    }

    @Override
    public List<ContractDTO> getAllContract() {
        Collection<Contract> all = contractRepository.findAll();
        return contractConverter.convertToDto(all);
    }

    @Override
    public ContractDTO getById(Integer id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException(CONTRACT_NOT_FOUND_MESSAGE + id));
        return contractConverter.convertToDto(contract);
    }

    @Override
    @Transactional
    public Integer createContract(ContractDTO contractToCreate) {
        contractValidator.validateContract(contractToCreate);
        Contract contract = contractConverter.convertToEntity(contractToCreate);
        Contract saveContract = contractRepository.save(contract);
        return saveContract.getId();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(CONTRACT_NOT_FOUND_MESSAGE + id));
        contractRepository.delete(contract);

    }

    @Override
    public ContractDTO updateContract(Integer id, ContractDTO contractToUpdate) {
        contractValidator.validateContract(contractToUpdate);
        contractRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException(CONTRACT_NOT_FOUND_MESSAGE + id));
        Contract entityToUpdate = contractConverter.convertToEntity(contractToUpdate);
        entityToUpdate.setId(id);
        Contract updateEntity = contractRepository.save(entityToUpdate);
        return contractConverter.convertToDto(updateEntity);
    }
}