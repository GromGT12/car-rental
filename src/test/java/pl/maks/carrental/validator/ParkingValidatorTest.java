package pl.maks.carrental.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import pl.maks.carrental.repository.ParkingRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ParkingValidatorTest {

    private final ParkingRepository parkingRepository = mock(ParkingRepository.class);
    private final ParkingValidator target = new ParkingValidator(parkingRepository);
    private final ParkingDTO parkingDTO = mock(ParkingDTO.class);

    @Test
    @DisplayName("A validation error should not be thrown when the parking input is valid.")
    void shouldNotThrow_whenParkingsIsValid() {
        //given
        ParkingDTO validParkings = validParkings();

        //when
        assertDoesNotThrow(() -> target.parkingValidation(validParkings));

        //then
    }

    @Test
    @DisplayName("Validation Error should be thrown when parkings is blank")
    void shouldThrow_whenParkingsIsBlank() {
        //given
        ParkingDTO emptyParkings = emptyParkings();
        String expectedMessage = "lastName is blank";

        //when
        CarRentalValidationException carRentalValidationException = assertThrows(CarRentalValidationException.class, () -> target.parkingValidation(emptyParkings));

        //then
        assertThat(carRentalValidationException.getViolations()).contains(expectedMessage);
    }
    @Test
    @DisplayName("Validation Error should be thrown when parkings is invalid")
    void shouldThrow_whenPhoneIsInvalid() {
        //given
        ParkingDTO invalidPhone = invalidPhone();
        String expectedMessage = String.format("%s can contain only digits: '%s'", "phone", parkingDTO.getPhone());

        //when
        CarRentalValidationException carRentalValidationException = assertThrows(CarRentalValidationException.class, () -> target.parkingValidation(invalidPhone));

        //then
        assertThat(carRentalValidationException.getViolations().contains(expectedMessage));
    }

    private ParkingDTO invalidPhone() {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setName("TestName");
        parkingDTO.setPhone("36007121");
        return parkingDTO;
    }

    private ParkingDTO validParkings () {
            ParkingDTO parkingDTO = new ParkingDTO();
            parkingDTO.setName("TestName");
            parkingDTO.setPhone("36007121");
            return parkingDTO;

        }
        private ParkingDTO emptyParkings () {
            ParkingDTO parkingDTO = new ParkingDTO();
            parkingDTO.setName("TestName");
            parkingDTO.setPhone("36007121");
            return parkingDTO;

        }
    }