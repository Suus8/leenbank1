package project.demo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    //region ATTRIBUTES
    private final String VALID_USERNAME = "olifa~ntje123";
    private final String VALID_PASSWORD = "FonTysROCKS!";
    private final String STRING_EXISTING_USERNAME = "Deze gebruikersnaam bestaat al, kies een andere.";
    //endregion

    //region METHODS
    /**
     * @verifies if all attributes of a login are correctly saved
     * @see Login#Login(String, String)
     */
    @Test
    public void constructor_createValidLoginWithAllAttributes() {// TODO
        // Arrange

        // Act

        // Assert

    }

    /**
     * @verifies if the entered username is unique, i.o.w. no other AbstractClient has the same username
     * @see Login#Login(String, String)
     */
    @Test
    public void constructor_checkIfUserNameIsUniqueAndThrowIllegalArgumentExceptionIfNot() throws IllegalArgumentException {
        // Arrange
        final String SECOND_VALID_USERNAME = "jantjuh2022";
        final String THIRD_VALID_USERNAME = "erikOpVrijdag@MIW!";

        // Act
        Login login1 = new Login(VALID_USERNAME, VALID_PASSWORD);
        Login login2 = new Login(VALID_USERNAME, VALID_PASSWORD);
        Login login3 = new Login(SECOND_VALID_USERNAME, VALID_PASSWORD);
        Login login4 = new Login(THIRD_VALID_USERNAME, VALID_PASSWORD);
        Login login5 = new Login(SECOND_VALID_USERNAME, VALID_PASSWORD);

        // Assert
        assertEquals(VALID_USERNAME, login1.getUsername());
        assertEquals(SECOND_VALID_USERNAME, login3.getUsername());
        assertEquals(THIRD_VALID_USERNAME, login4.getUsername());

        assertNull(login2, STRING_EXISTING_USERNAME);
        assertNull(login5, STRING_EXISTING_USERNAME);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Login lambdaTestLogin = new Login(VALID_USERNAME, VALID_PASSWORD);
        });
    }

    /**
     * @verifies if all passwaord requirements are met:
     * - at least 2 characters long
     * @see Login#Login(String, String)
     */
    @ParameterizedTest
    @MethodSource("validPasswords")
    public void constructor_checkPasswordRequirements(String password) {
        // Act
        Login loginSUT = new Login(VALID_USERNAME, password);

        // Assert
        assertEquals(password, loginSUT.getPassword());
    }

    /**
     * @verifies if an IllegalArgumentException is thrown if one of the password requirements is not met
     * @see Login#Login(String, String)
     */
    @ParameterizedTest
    @MethodSource("invalidPasswords")
    public void constructor_shouldThrowInvalidArgumentExceptionWhenPasswordRequirementsAreNotMet(String password) throws IllegalArgumentException {
        // Arrange, Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Login lambdaloginSUT = new Login(VALID_USERNAME, password);
        });
    }
    //endregion

    //region Helper Methods
    private static Stream<String> validPasswords() {
        return Stream.of(
                "123", "abc", "Ab", "1a2b", "*/", "-1", "abcdefghijklmnopqrstuvwxyz0123456789"
        );
    }

    private static Stream<String> invalidPasswords() {
        return Stream.of(
                "1", "a", "A", "*", "$"
        );
    }

    //endregion
}
