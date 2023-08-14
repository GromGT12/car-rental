package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.service.ClientService;

import java.util.List;

@Tag(name = "Client management API", description = "API for CRUD operation with clients")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Tag(name = "Fetching a client by ID", description = "In case the client is not found, the response will have a status of 404")
    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @Tag(name = "Retrieving all client", description = "In case no client is found, the response will have a status of 404")
    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @Tag(name = "Creating a new client", description = "In case the client is not created, the response will have a status of 404")
    @PostMapping
    public Integer CreateClient(@RequestBody @Valid ClientDTO clientToCreate) {
        return clientService.createClient(clientToCreate);
    }

    @Tag(name = "Delete a client by ID", description = "In case the client is not deleted by ID, the response will have a status of 404")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }

    @Tag(name = "Updating a client by ID", description = "In case the client is not updated correctly by ID, the response will have a status of 404")
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Integer id, @RequestBody @Valid ClientDTO clientToUpdate) {
        return clientService.updateClient(id, clientToUpdate);
    }
}


