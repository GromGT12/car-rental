package pl.maks.carrental.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.convertor.ParkingConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.ParkingRepository;
import pl.maks.carrental.repository.model.Parking;
import pl.maks.carrental.service.ParkingService;
import pl.maks.carrental.validator.ParkingValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ParkingServiceImp implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingValidator parkingValidator;
    private final ParkingConverter parkingConverter;

    public ParkingServiceImp(ParkingRepository parkingRepository, ParkingValidator parkingValidator, ParkingConverter parkingConverter) {
        this.parkingRepository = parkingRepository;
        this.parkingValidator = parkingValidator;
        this.parkingConverter = parkingConverter;
    }

    @Override
    public List<ParkingDTO> getAllParkings() {
        Collection<Parking> all = parkingRepository.findAll();
        return parkingConverter.convertToDto(all);

    }

    @Override
    public ParkingDTO getById(Integer id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Parking not found" + id));
        return parkingConverter.convertToDto(parking);

    }

    @Override
    @Transactional
    public Integer createParking(ParkingDTO parkingToCreate) {
        parkingValidator.parkingValidation(parkingToCreate);
        Parking parking = parkingConverter.convertToEntity(parkingToCreate);
        Parking saveParking = parkingRepository.save(parking);
        return saveParking.getId();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Parking not found" + id));
        parkingRepository.delete(parking);
    }

    @Override
    public ParkingDTO updateParking(Integer id, ParkingDTO parkingToUpdate) {
        parkingValidator.parkingValidation(parkingToUpdate);
        Parking parking = parkingRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Parking not found" + id));
        Parking entityToUpdate = parkingConverter.convertToEntity(parkingToUpdate);
        entityToUpdate.setId(id);
        Parking updateEntity = parkingRepository.save(entityToUpdate);
        return parkingConverter.convertToDto(updateEntity);
    }
}
