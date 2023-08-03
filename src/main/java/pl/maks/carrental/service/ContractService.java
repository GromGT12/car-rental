package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.ContractDTO;

import java.util.List;

public interface ContractService {
    List<ContractDTO> getAllContract();

    ContractDTO getById(Integer id);

    Integer createContract(ContractDTO contractDTO);

    void deleteById(Integer id);

    ContractDTO updateContract(Integer id, ContractDTO contractToUpdate);

}
