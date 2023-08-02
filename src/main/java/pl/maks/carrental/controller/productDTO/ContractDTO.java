package pl.maks.carrental.controller.productDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class ContractDTO {
    private Integer id;
    private CarDTO car;
    private ClientDTO client;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractDTO that = (ContractDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(car, that.car) && Objects.equals(client, that.client) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, client, startDate, endDate, price);
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "id=" + id +
                ", car=" + car +
                ", client=" + client +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}
