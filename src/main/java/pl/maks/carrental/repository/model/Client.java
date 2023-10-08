package pl.maks.carrental.repository.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "accidents")
    private Integer accidents;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(String firstName, String lastName, String documentNumber, Integer accidents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.accidents = accidents;
    }

    public Client(Integer id, String firstName, String lastName, String documentNumber, Integer accidents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.accidents = accidents;
    }

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

}
