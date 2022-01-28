package project.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import project.demo.utility.AccountToIbanConverter;

public class ConnectionTerminalDTO {

    private String accountNumberCashier;
    private int validationCode;

    @JsonCreator
    public ConnectionTerminalDTO(
            @JsonProperty("accountNumber") String accountNumber,
            @JsonProperty("validationCode") int validationCode
    ) {
        this.accountNumberCashier = AccountToIbanConverter.convertAccountToIban(accountNumber);
        this.validationCode = validationCode;
    }

    public String getAccountNumberCashier() {
        return accountNumberCashier;
    }

    public int getConnectionCode() {
        return validationCode;
    }

}
