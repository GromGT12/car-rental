package pl.maks.carrental.service.impl;

import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ContractDTO;
import pl.maks.carrental.converter.ContractConverter;
import pl.maks.carrental.repository.ContractRepository;
import pl.maks.carrental.repository.model.Contract;
import pl.maks.carrental.service.ContractService;
import pl.maks.carrental.validator.ContractValidator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ContractServiceImplTest {
    private final ContractRepository contractRepository = mock(ContractRepository.class);
    private final ContractConverter contractConverter = mock(ContractConverter.class);
    private final ContractValidator contractValidator = mock(ContractValidator.class);
    private final ContractService target = new ContractServiceImpl(contractConverter, contractRepository, contractValidator);

    @Test
    void shouldCreateContract() {
        //given
        ContractDTO contractDTO = new ContractDTO();
        Contract contractToSave = new Contract();
        Contract savedContract = new Contract();

        when(contractConverter.convertToEntity(contractDTO)).thenReturn(contractToSave);
        when(contractRepository.save(contractToSave)).thenReturn(savedContract);

        //when
        Integer actualId = target.createContract(contractDTO);

        //then
        verify(contractValidator).validateContract(contractDTO);
        verify(contractConverter).convertToEntity(contractDTO);

        assertThat(actualId).isEqualTo(savedContract.getId());
    }

    @Test
    void shouldUpdateContract() {
        // given
        ContractDTO updatedContractDTO = new ContractDTO();
        Contract updatedContract = new Contract();
        Contract saveContract = new Contract();

        when(contractConverter.convertToEntity(updatedContractDTO)).thenReturn(updatedContract);
        when(contractRepository.findById(anyInt())).thenReturn(Optional.of(saveContract));
        when(contractRepository.save(updatedContract)).thenReturn(updatedContract);
        when(contractConverter.convertToDto(updatedContract)).thenReturn(updatedContractDTO);

        // when
        ContractDTO actualContractDTO = target.updateContract(55, updatedContractDTO);

        // then
        verify(contractValidator).validateContract(updatedContractDTO);
        verify(contractRepository).findById(55);
        verify(contractConverter).convertToEntity(updatedContractDTO);
        verify(contractConverter).convertToDto(updatedContract);

        assertThat(actualContractDTO.getId()).isEqualTo(saveContract.getId());
    }

    @Test
    void shouldGetContractById() {
        // given
        Integer contractId = 12;
        Contract contractToReturn = new Contract();
        ContractDTO saveContractDTO = new ContractDTO();

        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contractToReturn));
        when(contractConverter.convertToDto(contractToReturn)).thenReturn(saveContractDTO);

        // when
        ContractDTO actualContractDTO = target.getById(contractId);

        // then
        verify(contractRepository).findById(contractId);
        verify(contractConverter).convertToDto(contractToReturn);

        assertThat(actualContractDTO).isEqualTo(saveContractDTO);
    }

    @Test
    void shouldGetAllContracts() {
        // given
        List<Contract> contracts = Arrays.asList(new Contract(), new Contract());
        List<ContractDTO> saveContractDTOs = Arrays.asList(new ContractDTO(), new ContractDTO());

        when(contractRepository.findAll()).thenReturn(contracts);
        when(contractConverter.convertToDto(contracts)).thenReturn(saveContractDTOs);

        // when
        List<ContractDTO> actualContractDTOs = target.getAllContract();

        // then
        verify(contractRepository).findAll();
        verify(contractConverter).convertToDto(contracts);

        assertThat(actualContractDTOs).isEqualTo(saveContractDTOs);
    }
}
