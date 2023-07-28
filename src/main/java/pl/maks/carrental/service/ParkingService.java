package pl.maks.carrental.service;

import pl.maks.carrental.controller.productDTO.ParkingDTO;

import java.util.List;

public interface ParkingService {
    List<ParkingDTO> getAllParkings();
    ParkingDTO getById(Integer id);
    Integer createParking(ParkingDTO parkingDTO);
    void deleteById(Integer id);
}
