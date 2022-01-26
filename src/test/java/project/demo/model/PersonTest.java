package project.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonTest {
    //region FINAL ATTRIBUTES
    private final Long VALID_SSN = 123456789L;
    //endregion

    //region TEST METHODS
    /**
     * @verifies if all attributes of a person are correctly saved
     * @see Person#Person(Long, Login, ClientDetails, PersonDetails)
     */
    @Test
    public void constructor_createValidPersonWithAllAttributes() {
        // Arrange
        String username = "pietje123";
        String password = "PeTeRsEn2022!";
        String email = "p.petersen@home.nl";
        String street = "Dorpstraat";
        int houseNumber = 1;
        String houseNumberAddition = "a";
        String zip = "1234AZ";
        String city = "Eindhoven";
        String firstName = "Piet";
        String lastName = "Petersen";
        String dateOfBirth = "2000-01-01";
        // Arrange mock classes
        Login mockLogin = mock(Login.class);
        when(mockLogin.getUsername()).thenReturn(username);
        when(mockLogin.getPassword()).thenReturn(password);

        ClientDetails mockClientDetails = mock(ClientDetails.class);
        when(mockClientDetails.getEmail()).thenReturn(email);
        when(mockClientDetails.getStreet()).thenReturn(street);
        when(mockClientDetails.getHouseNumber()).thenReturn(houseNumber);
        when(mockClientDetails.getHouseNumberAddition()).thenReturn(houseNumberAddition);
        when(mockClientDetails.getZipCode()).thenReturn(zip);
        when(mockClientDetails.getCity()).thenReturn(city);

        Name mockName = mock(Name.class);
        PersonDetails mockPersonDetails = mock(PersonDetails.class);
        when(mockName.getFirstName()).thenReturn(firstName);
        when(mockName.getLastName()).thenReturn(lastName);
        when(mockPersonDetails.getFullName()).thenReturn(mockName);
        when(mockPersonDetails.getDateOfBirth()).thenReturn(dateOfBirth);

        // Act
        Person personSUT = new Person(VALID_SSN,mockLogin,mockClientDetails,mockPersonDetails);

        // Assert
        assertEquals(VALID_SSN, personSUT.getId());

        assertEquals(username, personSUT.getLogin().getUsername());
        assertEquals(password, personSUT.getLogin().getPassword());

        assertEquals(email, personSUT.getClientDetails().getEmail());
        assertEquals(street, personSUT.getClientDetails().getStreet());
        assertEquals(houseNumber, personSUT.getClientDetails().getHouseNumber());
        assertEquals(houseNumberAddition, personSUT.getClientDetails().getHouseNumberAddition());
        assertEquals(zip, personSUT.getClientDetails().getZipCode());
        assertEquals(city, personSUT.getClientDetails().getCity());

        assertEquals(dateOfBirth, personSUT.getPersonDetails().getDateOfBirth());
        assertEquals(firstName, personSUT.getPersonDetails().getFullName().getFirstName());
        assertEquals(lastName, personSUT.getPersonDetails().getFullName().getLastName());;

    }

    /**
     * @verifies if the person is already registered (the BSN number is already in use) and use the existing Person
     * instead of creating a new Person object
     * @see Person#Person(Long, Login, ClientDetails, PersonDetails)
     */
    @Test
    public void constructor_checkIfSSNExistsAndReturnExistingPerson() {
        // Arrange

        // Arrange mock classes
        Login mockLogin = mock(Login.class);
        ClientDetails mockClientDetails = mock(ClientDetails.class);
        PersonDetails mockPersonDetails = mock(PersonDetails.class);

        // Act
        Person personSUT = new Person(VALID_SSN,mockLogin,mockClientDetails,mockPersonDetails);

        // Assert
        fail();
        //TODO
    }
    //endregion


}
