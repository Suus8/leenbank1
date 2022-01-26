package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Locale;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    //endregion

    //region CONSTRUCTOR

    /**
     * Constructor for creating a Name object containing all name details of a customer or employee of LeenmanBank.
     *
     * @param firstName First name of customer/employee
     * @param lastName  Last name of customer/employee
     *                  Note: at this point both fields are obligatory but more (optional) attributes (such as title, insertion...) can be added
     * @should Create a valid Name with all above details
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //endregion

    //region METHODS
    public String getFullNameAsString() {
        return lastName + ", " + firstName.toUpperCase(Locale.ROOT).charAt(0);
    }
    //endregion

    //region Helper Methods

    //endregion

    //region GETTERS & SETTERS
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
    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
    //endregion
}
