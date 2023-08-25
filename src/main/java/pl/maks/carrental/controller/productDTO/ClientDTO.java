package pl.maks.carrental.controller.productDTO;

import java.io.Serializable;
import java.util.Objects;

public class ClientDTO implements Serializable {

    private Integer id;

    private String firstName;
    private String lastName;

    private String documentNumber;
    private Integer accidents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getAccidents() {
        return accidents;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id) && Objects.equals(firstName, clientDTO.firstName) && Objects.equals(lastName, clientDTO.lastName) && Objects.equals(documentNumber, clientDTO.documentNumber) && Objects.equals(accidents, clientDTO.accidents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, documentNumber, accidents);
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", accidents=" + accidents +
                '}';
    }
}