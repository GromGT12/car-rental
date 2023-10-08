package pl.maks.carrental.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.maks.carrental.controller.productDTO.ParkingDTO;
import pl.maks.carrental.repository.model.Parking;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingConverterTest {
    private static final Integer ID = 33;
    private static final String NAME = "TestParking";
    private static final String PHONE = "123-456-789";

    private final ParkingConverter target = new ParkingConverter();

    @Test
    @DisplayName("Should convert ParkingDTO to Parking")
    void shouldConvertParkingDtoToEntity() {
        // given
        ParkingDTO parkingDTO = parkingDTO();
        Parking expected = entityParking();

        // when
        Parking actual = target.convertToEntity(parkingDTO);

        // then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
    }

    @Test
    @DisplayName("Should convert Parking to DTO")
    void shouldConvertParkingToDto() {
        // given
        Parking parking = entityParking();
        ParkingDTO expected = parkingDTO();

        // when
        ParkingDTO actual = target.convertToDto(parking);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should convert Collection<Parkings> to List<ParkingDTO>")
    void shouldConvertCollectionParkingsToListParkingsDTO() {
        // given
        Collection<Parking> parkings = List.of(entityParking());
        List<ParkingDTO> expected = List.of(parkingDTO());

        // when
        List<ParkingDTO> actual = target.convertToDto(parkings);

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Parking entityParking() {
        Parking parking = new Parking();
        parking.setId(ID);
        parking.setName(NAME);
        parking.setPhone(PHONE);
        return parking;
    }

    private ParkingDTO parkingDTO() {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(ID);
        parkingDTO.setName(NAME);
        parkingDTO.setPhone(PHONE);
        return parkingDTO;
    }
}
