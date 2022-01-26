package project.demo.dto;

import java.util.Objects;

public class PaymentResultDTO {

    boolean succeeded;
    int statuscode;
    String description;
    long transactionId;

    public PaymentResultDTO(boolean succeeded, int statuscode, long transactionId) {
        this.succeeded = succeeded;
        this.statuscode = statuscode;
        this.description = TransActionStatusCode.getDescriptionForStatusCode(statuscode);
        this.transactionId = transactionId;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentResultDTO)) return false;
        PaymentResultDTO that = (PaymentResultDTO) o;
        return succeeded == that.succeeded && statuscode == that.statuscode && transactionId == that.transactionId && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(succeeded, statuscode, description, transactionId);
    }
}
