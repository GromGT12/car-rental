package pl.maks.carrental.controller;

import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/{id}")
    public ContractDTO getById(@PathVariable Integer id) {
        return contractService.getById(id);
    }

    @GetMapping
    public List<ContractDTO> getAll() {
        return contractService.getAllContract();
    }

    @PostMapping
    public Integer CreateContract(@RequestBody ContractDTO contractToCreate) {
        return contractService.createContract(contractToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contractService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ContractDTO update(@PathVariable Integer id, @RequestBody ContractDTO contractToUpdate) {
        return contractService.updateContract(id, contractToUpdate);
    }
}

