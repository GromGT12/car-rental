package pl.maks.carrental.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
