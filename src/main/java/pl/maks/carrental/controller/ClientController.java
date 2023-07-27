package pl.maks.carrental.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @PostMapping
    public Integer CreateClient(@RequestBody @Valid ClientDTO userToCreate) {
        return clientService.createClient(userToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }
}
