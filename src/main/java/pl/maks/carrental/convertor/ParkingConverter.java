package pl.maks.carrental.convertor;

import org.springframework.stereotype.Component;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.repository.model.Parking;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingConverter {
    public Parking convertToEntity(ParkingDTO source) {
        return new Parking(source.getId(),
                source.getName(),
                source.getPhone());
    }

    public ParkingDTO convertToDto(Parking source) {
        return convertParkingToDto(source);
    }

    public List<ParkingDTO> convertToDto(Collection<Parking> source) {
        return source.stream()
                .map(this::convertParkingToDto)
                .collect(Collectors.toList());
    }

    public ParkingDTO convertParkingToDto(Parking source) {
        ParkingDTO result = new ParkingDTO();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setPhone(source.getPhone());
        return result;
    }
}

