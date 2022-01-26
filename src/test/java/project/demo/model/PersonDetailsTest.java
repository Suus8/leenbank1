package project.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonDetailsTest {
    //region ATTRIBUTES
    private final String VALID_DATE_OF_BIRTH = "2000-01-01";
    //endregion

    //region TEST METHODS
    /**
     * @verifies if all attributes of a PersonDetails are correctly saved
     * @see PersonDetails#PersonDetails(Name, String) 
     */
    @Test
    public void constructor_createValidLoginWithAllAttributes() {
        // Arrange
        String firstName = "Piet";
        String lastName = "Petersen";
        // Arrange mock classes
        Name name = mock(Name.class);
        when(name.getFirstName()).thenReturn(firstName);
        when(name.getLastName()).thenReturn(lastName);

        // Act
        PersonDetails personDetailsSUT = new PersonDetails(name,VALID_DATE_OF_BIRTH);

        // Assert
        assertEquals(VALID_DATE_OF_BIRTH, personDetailsSUT.getDateOfBirth());
        assertEquals(firstName, personDetailsSUT.getFullName().getFirstName());
        assertEquals(lastName, personDetailsSUT.getFullName().getLastName());

    }
    //endregion

    //region Helper Methods

    //endregion
}
