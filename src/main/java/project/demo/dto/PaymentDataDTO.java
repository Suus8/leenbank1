package project.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import project.demo.utility.AccountToIbanConverter;


public class PaymentDataDTO {

    String ibanSender;
    String ibanReceiver;
    long amountInCents;
    int pin;
    String description;
    long idPaymentMachine;
    final String PIN_PAYMENT_DESCRIPTION_STUB = "Pin betaling aan: ";

    @JsonCreator
    public PaymentDataDTO(
            @JsonProperty("accountNumber") String accountNumber,
            @JsonProperty("pin") int pin,
            @JsonProperty("amount") double amount,
            @JsonProperty("idPaymentMachine") long idPaymentMachine
    ) {
        this.ibanSender = AccountToIbanConverter.convertAccountToIban(accountNumber);
        this.pin = pin;
        this.amountInCents = Math.round(amount * 100);
        this.idPaymentMachine = idPaymentMachine;
        this.description = PIN_PAYMENT_DESCRIPTION_STUB;
        this.ibanReceiver = "";
    }

    public PaymentDataDTO(String ibanSender, String ibanReceiver, long amountInCents, String description) {
        this.ibanSender = ibanSender;
        this.ibanReceiver = ibanReceiver;
        this.amountInCents = amountInCents;
        this.pin = -1;
        this.description = description;
        this.idPaymentMachine = 0;
    }

    public String getIbanSender() {
        return ibanSender;
    }

    public String getIbanReceiver() {
        return ibanReceiver;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public int getPin() {
        return pin;
    }

    public String getDescription() {
        return description;
    }

    public long getIdPaymentMachine() {
        return idPaymentMachine;
    }

    public void addToDescription(String s) {
        description += s;
    }
}
