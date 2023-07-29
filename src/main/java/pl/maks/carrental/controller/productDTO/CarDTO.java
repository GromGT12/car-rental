package pl.maks.carrental.controller.productDTO;

import java.io.Serializable;
import java.util.Objects;

public class CarDTO implements Serializable{
    private Integer id;
    private String brand;
    private String model;
    private String classCar;
    private String fuel;
    private Double pricePerDay;
    private Integer parkingId;

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

    public String getClassCar() {
        return classCar;
    }

    public void setClassCar(String classCar) {
        this.classCar = classCar;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id) && Objects.equals(brand, carDTO.brand) && Objects.equals(model, carDTO.model) && Objects.equals(classCar, carDTO.classCar) && Objects.equals(fuel, carDTO.fuel) && Objects.equals(pricePerDay, carDTO.pricePerDay) && Objects.equals(parkingId, carDTO.parkingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, classCar, fuel, pricePerDay, parkingId);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", classCar='" + classCar + '\'' +
                ", fuel='" + fuel + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", parkingId=" + parkingId +
                '}';
    }
}
