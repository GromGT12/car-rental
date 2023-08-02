package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.ContractDTO;

import java.util.List;

public interface ContractService {
    List<ContractDTO> getAllCars();

    ContractDTO getById(Integer id);

    Integer createContract(ContractDTO contractDTO);

    void deleteById(Integer id);

}
