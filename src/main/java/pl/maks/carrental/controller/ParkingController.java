package pl.maks.carrental.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.service.ParkingService;

import java.util.List;

@Tag(name = "Parking management API", description = "API for operation with parkings")
@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Tag(name = "Fetching a parking by ID", description = "In case the parking is not found, the response will have a status of 404")
    @GetMapping("/{id}")
    public ParkingDTO getById(@PathVariable Integer id) {
        return parkingService.getById(id);
    }

    @Tag(name = "Retrieving all parking", description = "In case no parking is found, the response will have a status of 404")
    @GetMapping
    public List<ParkingDTO> getAll() {
        return parkingService.getAllParkings();
    }

    @Tag(name = "Creating a new parking", description = "In case the parking is not created, the response will have a status of 404")
    @PostMapping
    public Integer createParking(@RequestBody @Valid ParkingDTO parkingToCreate) {
        return parkingService.createParking(parkingToCreate);
    }

    @Tag(name = "Delete a parking by ID", description = "In case the parking is not deleted by ID, the response will have a status of 404")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        parkingService.deleteById(id);
    }

    @Tag(name = "Updating a parking by ID", description = "In case the parking is not updated correctly by ID, the response will have a status of 404")
    @PutMapping("/{id}")
    public ParkingDTO update(@PathVariable Integer id, @RequestBody @Valid ParkingDTO parkingToUpdate) {
        return parkingService.updateParking(id, parkingToUpdate);
    }
}
