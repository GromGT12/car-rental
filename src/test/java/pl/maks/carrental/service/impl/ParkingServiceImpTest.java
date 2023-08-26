package pl.maks.carrental.service.impl;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.convertor.ParkingConverter;
import pl.maks.carrental.repository.ParkingRepository;
import pl.maks.carrental.repository.model.Parking;
import pl.maks.carrental.service.ParkingService;
import pl.maks.carrental.validator.ParkingValidator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ParkingServiceImpTest {
    private final ParkingRepository parkingRepository = mock(ParkingRepository.class);
    private final ParkingConverter parkingConverter = mock(ParkingConverter.class);
    private final ParkingValidator parkingValidator = mock(ParkingValidator.class);
    private final ParkingService target = new ParkingServiceImp(parkingRepository, parkingValidator, parkingConverter);

    @Test
    void shouldCreateParking() {
        //given
        ParkingDTO parkingDTO = new ParkingDTO();
        Parking parkingToSave = new Parking();
        Parking savedParking = new Parking();

        when(parkingConverter.convertToEntity(parkingDTO)).thenReturn(parkingToSave);
        when(parkingRepository.save(parkingToSave)).thenReturn(savedParking);

        //when
        Integer actualId = target.createParking(parkingDTO);

        //then
        verify(parkingValidator).parkingValidation(parkingDTO);
        verify(parkingConverter).convertToEntity(parkingDTO);

        assertThat(actualId).isEqualTo(savedParking.getId());
    }

    @Test
    void shouldUpdateParking() {
        // given
        ParkingDTO updatedParkingDTO = new ParkingDTO();
        Parking updatedParking = new Parking();
        Parking saveParking = new Parking();

        when(parkingConverter.convertToEntity(updatedParkingDTO)).thenReturn(updatedParking);
        when(parkingRepository.findById(anyInt())).thenReturn(Optional.of(saveParking));
        when(parkingRepository.save(updatedParking)).thenReturn(updatedParking);
        when(parkingConverter.convertToDto(updatedParking)).thenReturn(updatedParkingDTO);

        // when
        ParkingDTO actualParkingDTO = target.updateParking(99, updatedParkingDTO);

        // then
        verify(parkingValidator).parkingValidation(updatedParkingDTO);
        verify(parkingRepository).findById(99);
        verify(parkingConverter).convertToEntity(updatedParkingDTO);
        verify(parkingConverter).convertToDto(updatedParking);

        assertThat(actualParkingDTO.getId()).isEqualTo(saveParking.getId());
    }

    @Test
    void shouldGetParkingById() {
        // given
        Integer parkingId = 33;
        Parking parkingToReturn = new Parking(parkingId);
        ParkingDTO saveParkingDTO = new ParkingDTO();

        when(parkingRepository.findById(parkingId)).thenReturn(Optional.of(parkingToReturn));
        when(parkingConverter.convertToDto(parkingToReturn)).thenReturn(saveParkingDTO);

        // when
        ParkingDTO actualParkingDTO = target.getById(parkingId);

        // then
        verify(parkingRepository).findById(parkingId);
        verify(parkingConverter).convertToDto(parkingToReturn);

        assertThat(actualParkingDTO).isEqualTo(saveParkingDTO);
    }

    @Test
    void shouldGetAllParkings() {
        // given
        List<Parking> parkings = Arrays.asList(new Parking(66), new Parking(7));
        List<ParkingDTO> saveParkingDTOs = Arrays.asList(new ParkingDTO(), new ParkingDTO());

        when(parkingRepository.findAll()).thenReturn(parkings);
        when(parkingConverter.convertToDto(parkings)).thenReturn(saveParkingDTOs);

        // when
        List<ParkingDTO> actualParkingDTOs = target.getAllParkings();

        // then
        verify(parkingRepository).findAll();
        verify(parkingConverter).convertToDto(parkings);

        assertThat(actualParkingDTOs).isEqualTo(saveParkingDTOs);
    }
}
