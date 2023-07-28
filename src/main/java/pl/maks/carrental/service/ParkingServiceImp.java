package pl.maks.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.convertor.ParkingConverter;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.repository.SpringDataParkingsRepository;
import pl.maks.carrental.repository.model.Parking;
import pl.maks.carrental.validator.ParkingValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ParkingServiceImp implements ParkingService {

    private final SpringDataParkingsRepository parkingsRepository;
    private final ParkingValidator parkingValidator;
    private final ParkingConverter parkingConverter;

    public ParkingServiceImp(SpringDataParkingsRepository parkingsRepository, ParkingValidator parkingValidator, ParkingConverter parkingConverter) {
        this.parkingsRepository = parkingsRepository;
        this.parkingValidator = parkingValidator;
        this.parkingConverter = parkingConverter;
    }

    @Override
    public List<ParkingDTO> getAllParkings() {
        Collection<Parking> all = parkingsRepository.findAll();
        return parkingConverter.convertToDto(all);

    }

    @Override
    public ParkingDTO getById(Integer id) {
        Parking parking = parkingsRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Parking not found" + id));
        return parkingConverter.convertToDto(parking);

    }

    @Override
    @Transactional
    public Integer createParking(ParkingDTO parkingToCreate) {
        parkingValidator.validateParking(parkingToCreate);
        Parking parking = parkingConverter.convertToEntity(parkingToCreate);
        Parking saveParking = parkingsRepository.save(parking);
        return saveParking.getId();
    }

    @Override
    public void deleteById(Integer id) {
        Parking parking = parkingsRepository.findById(id).orElseThrow(() -> new CarRentalNotFoundException("Parking not found" + id));
        parkingsRepository.deleteById(id);
    }
}
