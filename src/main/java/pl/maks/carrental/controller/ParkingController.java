package pl.maks.carrental.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/{id}")
    public ParkingDTO getById(@PathVariable Integer id) {
        return parkingService.getById(id);
    }

    @GetMapping
    public List<ParkingDTO> getAll() {
        return parkingService.getAllParkings();
    }

    @PostMapping
    public Integer createParking(@RequestBody @Valid ParkingDTO parkingToCreate) {
        return parkingService.createParking(parkingToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        parkingService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ParkingDTO update(@PathVariable Integer id, @RequestBody @Valid ParkingDTO parkingToUpdate) {
        return parkingService.updateParking(id, parkingToUpdate);
    }
}