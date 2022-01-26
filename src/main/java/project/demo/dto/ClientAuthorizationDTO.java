package project.demo.dto;

public class ClientAuthorizationDTO {
    //region ATTRIBUTES
    String username;
    String iban;
    int code;
    //endregion

    // CONSTRUCTOR
    public ClientAuthorizationDTO(String username, String iban, int code) {
        this.username = username;
        this.iban = iban;
        this.code = code;
    }

    //region GETTER & SETTERS
    public String getUsername() {
        return username;
    }

    public String getIban() {
        return iban;
    }

    public int getCode() {
        return code;
    }
    //endregion
}
