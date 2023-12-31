package pl.maks.carrental.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarValidatorTest {
    private final CarValidator target = new CarValidator();

    @Test
    @DisplayName("Validation Error should not be thrown when input client is valid")
    void shouldNotThrow_whenCarIsValid() {
        //given
        CarDTO valid = validCar();

        //when
        assertDoesNotThrow(() -> target.carValidation(valid));

        //then
    }

    @Test
    @DisplayName("Validation Error should be thrown brand is blank")
    void shouldThrow_whenFieldNameIsBlank() {
        // given
        CarDTO emptyFieldName = emptyFieldName();
        String expectedPrefix = "Brand is blank";

        // when
        CarRentalValidationException carRentalValidationException =
                assertThrows(CarRentalValidationException.class, () -> target.carValidation(emptyFieldName));
        //then
        assertThat(carRentalValidationException.getViolations()).anyMatch(message -> message.startsWith(expectedPrefix));
    }

    @Test
    @DisplayName("Validation Error should be thrown when price is zero")
    void shouldThrow_whenPriceIsNull() {
        //given
        CarDTO nullPriceCar = nullPriceCar();
        String expectedMessage = "PricePerDay must not be null and greater than zero";

        //when
        CarRentalValidationException carRentalValidationException = assertThrows(CarRentalValidationException.class, () -> target.carValidation(nullPriceCar));

        //then
        assertThat(carRentalValidationException.getViolations()).contains(expectedMessage);
    }

    @Test
    @DisplayName(" It should throw an exception when the parking ID is zero")
    void shouldThrow_whenParkingIdIsNull() {
        // given
        CarDTO nullParkingId = nullParkingId();
        String expectedMessage = "Parking id is null";
        //when
        CarRentalValidationException carRentalValidationException =
                assertThrows(CarRentalValidationException.class, () -> target.carValidation(nullParkingId));
        //then
        assertThat(carRentalValidationException.getViolations()).contains(expectedMessage);
    }

    private CarDTO nullParkingId() {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("TestBrand");
        carDTO.setCarClass("TestCarClass");
        carDTO.setFuel("TestFuel");
        carDTO.setModel("TestModel");
        carDTO.setParking(new ParkingDTO());
        carDTO.setPricePerDay(new BigDecimal(150.0));
        return carDTO;
    }


    private CarDTO validCar() {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("TestBrand");
        carDTO.setCarClass("TestCarClass");
        carDTO.setFuel("TestFuel");
        carDTO.setModel("TestModel");
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(11);
        carDTO.setParking(parkingDTO);
        carDTO.setPricePerDay(BigDecimal.valueOf(150.0));
        return carDTO;
    }

    private CarDTO emptyFieldName() {
        CarDTO carDTO = new CarDTO();
        carDTO.setCarClass("TestCarClass");
        carDTO.setFuel("TestFuel");
        carDTO.setModel("TestModel");
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(11);
        carDTO.setParking(parkingDTO);
        carDTO.setPricePerDay(new BigDecimal(150.0));
        return carDTO;

    }

    private CarDTO nullPriceCar() {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand("TestBrand");
        carDTO.setCarClass("TestCarClass");
        carDTO.setFuel("TestFuel");
        carDTO.setModel("TestModel");
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(11);
        carDTO.setParking(parkingDTO);
        carDTO.setPricePerDay(new BigDecimal(0));
        return carDTO;
    }
}
