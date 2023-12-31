package pl.maks.carrental.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.CarDTO;
import pl.maks.carrental.controller.productDTO.ClientDTO;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.exception.CarRentalValidationException;
import java.math.BigDecimal;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ContractValidatorTest {
    private final ContractValidator target = new ContractValidator();
    protected final CarDTO carDTO = new CarDTO();
    private final ClientDTO clientDTO = new ClientDTO();

    @Test
    @DisplayName("Validation Error should be thrown when carId is null")
    void shouldThrowValidationExceptionWhenCarIdIsNull() {
        // given
        ContractDTO validContract = validContract();
        validContract.getCar().setId(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(validContract));
    }

    @Test
    @DisplayName("Validation Error should be thrown when clientId is null")
    void shouldThrowValidationExceptionWhenClientIdIsNull() {
        // given
        ContractDTO validContract = validContract();
        validContract.getClient().setId(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(validContract));
    }

    @Test
    @DisplayName("Validation Error should be thrown when car ID is null")
    void shouldThrow_whenCarIdIsNull() {
        // given
        ContractDTO carIdNull = CarIdNull();
        carIdNull.getCar().setId(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(carIdNull));
    }

    @Test
    @DisplayName("Validation Error should be thrown when client ID is null")
    void shouldThrow_whenClientIdIsNull() {
        // given
        ContractDTO clientIdNull = ClientIdNull();
        clientIdNull.getClient().setId(null);


        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(clientIdNull));
    }

    @Test
    @DisplayName("Validation Error should be thrown when start date is null")
    void shouldThrow_whenStartDateIsNull() {
        // given
        ContractDTO dateStart = DateStart();
        dateStart.setStartDate(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(dateStart));
    }

    @Test
    @DisplayName("Validation Error should be thrown when end date is null")
    void shouldThrow_whenEndDateIsNull() {
        // given
        ContractDTO endDate = EndDate();
        endDate.setEndDate(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(endDate));
    }

    @Test
    @DisplayName("Validation Error should be thrown when price is null")
    void shouldThrow_whenPriceIsNull() {
        // given
        ContractDTO priceIsNull = PriceIsNull();
        priceIsNull.setPrice(null);

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(priceIsNull));
    }

    @Test
    @DisplayName("Validation Error should be thrown when price is less than or equal to zero")
    void shouldThrow_whenPriceIsLessThanOrEqualToZero() {
        // given
        ContractDTO priceEqualToZero = PriceEqualToZero();
        priceEqualToZero.setPrice(BigDecimal.valueOf(-10.0));

        // when
        assertThrows(CarRentalValidationException.class, () -> target.validateContract(priceEqualToZero));
    }
    private ContractDTO validContract() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal("150.0"));
        return dto;
    }

    private ContractDTO CarIdNull() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(new CarDTO());
        dto.setClient(new ClientDTO());
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal("150.0"));
        return dto;
    }

    private ContractDTO ClientIdNull() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal("150.0"));
        return dto;
    }

    private ContractDTO DateStart() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal("150.0"));
        return dto;
    }

    private ContractDTO EndDate() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal("150.0"));
        return dto;
    }

    private ContractDTO PriceIsNull() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(new BigDecimal(0));
        return dto;
    }
    private ContractDTO PriceEqualToZero() {
        ContractDTO dto = new ContractDTO();
        dto.setCar(carDTO);
        dto.setClient(clientDTO);
        dto.setStartDate(Date.valueOf("2023-07-15"));
        dto.setEndDate(Date.valueOf("2023-07-16"));
        dto.setPrice(BigDecimal.valueOf(-10.0));
        return dto;
    }
}
