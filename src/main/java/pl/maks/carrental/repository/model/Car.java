package pl.maks.carrental.repository.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "class")
    private String classCar;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "price_per_da")
    private Double pricePerDay;
    @Column(name = "parking_id")
    private Integer parkingId;

    public Car() {
    }

    public Car(String brand, String model, String classCar, String fuel, Double pricePerDay, Integer parkingId) {
        this.brand = brand;
        this.model = model;
        this.classCar = classCar;
        this.fuel = fuel;
        this.pricePerDay = pricePerDay;
        this.parkingId = parkingId;
    }

    public Car(Integer id, String brand, String model, String classCar, String fuel, Double pricePerDay, Integer parkingId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.classCar = classCar;
        this.fuel = fuel;
        this.pricePerDay = pricePerDay;
        this.parkingId = parkingId;
    }

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
        Car cars = (Car) o;
        return Objects.equals(id, cars.id) && Objects.equals(brand, cars.brand) && Objects.equals(model, cars.model) && Objects.equals(classCar, cars.classCar) && Objects.equals(fuel, cars.fuel) && Objects.equals(pricePerDay, cars.pricePerDay) && Objects.equals(parkingId, cars.parkingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, classCar, fuel, pricePerDay, parkingId);
    }

    @Override
    public String toString() {
        return "Car{" +
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
