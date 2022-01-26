package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telephoneNumber; // TODO update UML
    private String street;
    private int houseNumber;
    private String houseNumberAddition;
    private String zipCode;
    private String city;
    //endregion

    //region CONSTRUCTOR
    /**
     * Constructor for creating a ClientDetails a client (customer, business, employee) of LeenmanBank containing
     * mainly contact details.
     *
     * @param email               Email address on which the clients are registered
     * @param telephoneNumber     Telephone number of client
     * @param street              Street name of client
     * @param houseNumber         House number of client
     * @param houseNumberAddition House number addition of client
     * @param zipCode             Zip code of client
     * @param city                City of client
     *
     * @should Create a valid ClientDetails with all above details
     * @should At least contain an email or address or telephone number //TODO
     */
    public ClientDetails(String email, String telephoneNumber, String street, int houseNumber, String houseNumberAddition, String zipCode, String city) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.zipCode = zipCode;
        this.city = city;
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods

    //endregion

    //region GETTERS & SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDetails that = (ClientDetails) o;
        return houseNumber == that.houseNumber && Objects.equals(email, that.email) && Objects.equals(street, that.street) && Objects.equals(houseNumberAddition, that.houseNumberAddition) && Objects.equals(zipCode, that.zipCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, street, houseNumber, houseNumberAddition, zipCode, city);
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseNumberAddition='" + houseNumberAddition + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
    //endregion
}
