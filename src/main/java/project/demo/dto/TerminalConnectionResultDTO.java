package project.demo.dto;

import java.util.Objects;

public class TerminalConnectionResultDTO {

    boolean succeeded;
    int statuscode;
    String description;
    long pinTerminalID;

    public TerminalConnectionResultDTO(boolean succeeded, int statuscode, long pinTerminalID) {
        this.succeeded = succeeded;
        this.statuscode = statuscode;
        this.description = ConnectionStatusCode.getConnectionCode(statuscode);
        this.pinTerminalID = pinTerminalID;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public String getDescription() {
        return description;
    }

    public long getPinTerminalID() {
        return pinTerminalID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminalConnectionResultDTO that = (TerminalConnectionResultDTO) o;
        return succeeded == that.succeeded && statuscode == that.statuscode && pinTerminalID == that.pinTerminalID && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(succeeded, statuscode, description, pinTerminalID);
    }
}
