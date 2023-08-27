package pl.maks.carrental.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParkingValidatorTest {

    private final ParkingValidator target = new ParkingValidator();

    @Test
    @DisplayName("Validation Error should be thrown when name is blank")
    void shouldThrow_whenNameIsBlank() {
        //given
        ParkingDTO emptyName = emptyName();
        String expectedMessage = String.format("%s is blank", "name");


        //when
        CarRentalValidationException carRentalValidationException = assertThrows(
                CarRentalValidationException.class, () -> target.parkingValidation(emptyName));

        //then
        assertThat(carRentalValidationException.getViolations()).contains(expectedMessage);
    }

    @Test
    @DisplayName("Validation Error should be thrown when phone is invalid")
    void shouldThrow_whenPhoneIsInvalid() {
        //given
        ParkingDTO invalidPhone = invalidPhone();
        String expectedMessage = String.format("phone can contain only digits: '%s'", invalidPhone.getPhone());

        //when
        CarRentalValidationException carRentalValidationException = assertThrows(
                CarRentalValidationException.class, () -> target.parkingValidation(invalidPhone));

        //then
        assertThat(carRentalValidationException.getViolations()).contains(expectedMessage);
    }

    private ParkingDTO invalidPhone() {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setName("TestName");
        parkingDTO.setPhone("invalid");
        return parkingDTO;
    }

    private ParkingDTO emptyName() {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setName("");
        parkingDTO.setPhone("5553355");
        return parkingDTO;
    }
}

