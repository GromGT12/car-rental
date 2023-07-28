package pl.maks.carrental.controller.productDTO;

import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class ParkingDTO{
    private Integer id;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String name;
    @Pattern(regexp = "^(?:\\+?\\d{1,4}\\s?)?(?:\\(\\d{1,4}\\)\\s?)?(?:[-.\\s]?\\d{1,5}){1,6}$")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingDTO that = (ParkingDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return "ParkingDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
