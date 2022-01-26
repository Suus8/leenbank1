package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientNumberOfTransactions {
    @Id
    Long clientId;
    Long numberOfTransactions;
    String clientName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Long numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientNumberOfTransactions that = (ClientNumberOfTransactions) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(numberOfTransactions, that.numberOfTransactions) && Objects.equals(clientName, that.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, numberOfTransactions, clientName);
    }

    @Override
    public String toString() {
        return "ClientNumberOfTransactions{" +
                "clientId=" + clientId +
                ", numberOfTransactions=" + numberOfTransactions +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
