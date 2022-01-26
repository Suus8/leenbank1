package project.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class ClientDetailsTest {
    //region ATTRIBUTES
    private static final String VALID_EMAIL = "ikbeneenstudent@student.fontys.nl";
    private static final String VALID_TEL_NUMBER = "0612345678L";
    private static final String VALID_STREET = "Dorpstraat";
    private static final int VALID_HOUSE_NUMBER = 123;
    private static final String VALID_HOUSE_NUMBER_ADDITION = "B-3c";
    private static final String VALID_ZIPCODE = "1234AZ";
    private static final String VALID_CITY = "Eindhoven";
    //endregion

    //region TEST METHODS
    /**
     * @verifies if all attributes of a ClientDetails are correctly saved
     * @see ClientDetails#ClientDetails(String, String, String, int, String, String, String)
     */
    @ParameterizedTest
    @MethodSource("validHouseNumberAdditions")
    public void constructor_createValidLoginWithAllAttributes(String houseNumberAddition) {
        // Arrange

        // Act
        ClientDetails clientDetailsSUT = new ClientDetails(VALID_EMAIL,VALID_TEL_NUMBER,VALID_STREET,VALID_HOUSE_NUMBER,houseNumberAddition,
                VALID_ZIPCODE,VALID_CITY);

        // Assert
        assertEquals(VALID_EMAIL,clientDetailsSUT.getEmail());
        assertEquals(VALID_STREET,clientDetailsSUT.getStreet());
        assertEquals(VALID_HOUSE_NUMBER,clientDetailsSUT.getHouseNumber());
        assertEquals(houseNumberAddition,clientDetailsSUT.getHouseNumberAddition());
        assertEquals(VALID_ZIPCODE,clientDetailsSUT.getZipCode());
        assertEquals(VALID_CITY,clientDetailsSUT.getCity());
    }
    //endregion

    //region Helper Methods
    private static Stream<String> validHouseNumberAdditions() {
        return Stream.of(
                VALID_HOUSE_NUMBER_ADDITION, "", "a", "A", "Gebouw 4, kamer 6"
        );
    }

    //endregion
}
