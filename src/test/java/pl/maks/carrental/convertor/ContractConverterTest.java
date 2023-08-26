package pl.maks.carrental.convertor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.repository.model.Contract;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ContractConverterTest {
    private static final Integer ID = 22;
    private static final LocalDate START_DATE = LocalDate.of(2023, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 1, 5);
    private static final Double PRICE = 100.0;

    private final CarConverter carConverter = mock(CarConverter.class);
    private final ClientConverter clientConverter = mock(ClientConverter.class);
    private final ContractConverter target = new ContractConverter(carConverter, clientConverter);

    @Test
    @DisplayName("Should convert ContractDTO to Contract")
    void shouldConvertContractDtoToEntity() {
        // given
        ContractDTO contractDTO = contractDTO();
        Contract expected = entityContract();

        // when
        Contract actual = target.convertToEntity(contractDTO);

        // then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getStartDate()).isEqualTo(expected.getStartDate());
        assertThat(actual.getEndDate()).isEqualTo(expected.getEndDate());
        assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
    }

    @Test
    @DisplayName("Should convert Contract to DTO")
    void shouldConvertContractToDto() {
        // given
        Contract contract = entityContract();
        ContractDTO expected = contractDTO();

        // when
        ContractDTO actual = target.convertToDto(contract);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should convert Collection<Contracts> to List<ContractDTO>")
    void shouldConvertCollectionContractsToListContractsDTO() {
        // given
        Collection<Contract> contracts = List.of(entityContract());
        List<ContractDTO> expected = List.of(contractDTO());

        // when
        List<ContractDTO> actual = target.convertToDto(contracts);

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Contract entityContract() {
        Contract contract = new Contract();
        contract.setId(ContractConverterTest.ID);
        contract.setStartDate(Date.valueOf(START_DATE));
        contract.setEndDate(Date.valueOf(END_DATE));
        contract.setPrice(BigDecimal.valueOf(PRICE));
        return contract;
    }

    private ContractDTO contractDTO() {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(ID);
        contractDTO.setStartDate(Date.valueOf(START_DATE));
        contractDTO.setEndDate(Date.valueOf(END_DATE));
        contractDTO.setPrice(BigDecimal.valueOf(PRICE));
        return contractDTO;
    }
}
