package project.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    //region ATTRIBUTES
    private final String VALID_FIRST_NAME = "Freddy";
    private final String VALID_LAST_NAME = "Von Thunder";
    //endregion

    //region TEST METHODS
    /**
     * @verifies if all attributes of a Name are correctly saved
     * @see Name#Name(String, String)
     */
    @Test
    public void constructor_createValidLoginWithAllAttributes() {
        // Arrange

        // Act
        Name nameSUT = new Name(VALID_FIRST_NAME,VALID_LAST_NAME);

        // Assert
        assertEquals(VALID_FIRST_NAME,nameSUT.getFirstName());
        assertEquals(VALID_LAST_NAME,nameSUT.getLastName());

    }
    //endregion

    //region Helper Methods

    //endregion
}
