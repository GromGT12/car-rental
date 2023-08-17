package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.service.ContractService;

import java.util.List;

@Tag(name = "Contract management API", description = "API for operation with contracts")
@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Tag(name = "Fetching a contract by ID", description = "In case the contract is not found, the response will have a status of 404")
    @GetMapping("/{id}")
    public ContractDTO getById(@PathVariable Integer id) {
        return contractService.getById(id);
    }

    @Tag(name = "Retrieving all contract", description = "In case no contract is found, the response will have a status of 404")
    @GetMapping
    public List<ContractDTO> getAll() {
        return contractService.getAllContract();
    }

    @Tag(name = "Creating a new contract", description = "In case the contract is not created, the response will have a status of 404")
    @PostMapping
    public Integer CreateContract(@RequestBody ContractDTO contractToCreate) {
        return contractService.createContract(contractToCreate);
    }

    @Tag(name = "Delete a contract by ID", description = "In case the contract is not deleted by ID, the response will have a status of 404")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contractService.deleteById(id);
    }

    @Tag(name = "Updating a contract by ID", description = "In case the contract is not updated correctly by ID, the response will have a status of 404")
    @PutMapping("/{id}")
    public ContractDTO update(@PathVariable Integer id, @RequestBody ContractDTO contractToUpdate) {
        return contractService.updateContract(id, contractToUpdate);
    }
}

