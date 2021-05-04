package entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
