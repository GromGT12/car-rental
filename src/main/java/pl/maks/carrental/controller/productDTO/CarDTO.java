package pl.maks.carrental.controller.productDTO;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDTO {
    private Integer id;
    private String brand;
    private String model;
    private String carClass;
    private String fuel;
    private BigDecimal pricePerDay;
    private ParkingDTO parking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public ParkingDTO getParking() {
        return parking;
    }

    public void setParking(ParkingDTO parking) {
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id) && Objects.equals(brand, carDTO.brand) && Objects.equals(model, carDTO.model) && Objects.equals(carClass, carDTO.carClass) && Objects.equals(fuel, carDTO.fuel) && Objects.equals(pricePerDay, carDTO.pricePerDay) && Objects.equals(parking, carDTO.parking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, carClass, fuel, pricePerDay, parking);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", fuel='" + fuel + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", parking=" + parking +
                '}';
    }
}
