package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractClient {
    //region ATTRIBUTES
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_details", referencedColumnName = "id")
    private PersonDetails personDetails;
    //endregion

    //region CONSTRUCTOR
    /**
     * Constructor for creating a customer of LeenmanBank containing login in, personal and contact details.
     *
     * @param id                 Social security number of customer
     *
     * @should Create a valid person with all above details
     * @should Check if the person is already registered (same BSN) and not create a new instance of Person when so
     * but return existing one //
     */
    public Person(Long id, Login login, ClientDetails contactDetails, PersonDetails personDetails) {
        super(id, login, contactDetails);
        // Check if SSN = unique, is done by DB through abstractclient class
            this.personDetails = personDetails;
            Account account = new Account(this, AccountType.CONSUMER);
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods (private)
    private List<Long> getAllSSN() {
        List<Long> numbers = new ArrayList<>();

        // Get all BSN from DAO/Repo
//        for (Integer bsn : ?? ){
//            numbers.add(bsn);
//        }
        //TODO

        return numbers;
    }
    //endregion

    //region GETTERS & SETTERS

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    @Override
    public String toString() {
        return super.toString() + "Person{" +
                "personDetails=" + personDetails +
                '}';
    }

//endregion
}
