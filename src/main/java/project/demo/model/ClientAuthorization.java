package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"authorized_client_id","iban_to_be_authorized_id"})})
public class ClientAuthorization {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @NotNull
    private AbstractClient authorizedClient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @NotNull
    private Account ibanToBeAuthorized;

    @NotNull
    private int code;
    private boolean authorizationAccepted = false;
    // TODO: timestamp + remove after x hours
    //endregion

    //region CONSTRUCTOR
    public ClientAuthorization(AbstractClient authorizedClient, Account ibanToBeAuthorized, int code) {
        this.authorizedClient = authorizedClient;
        this.ibanToBeAuthorized = ibanToBeAuthorized;
        this.code = code;
    }
    //endregion

    //region GETTER & SETTERS
    public Long getId() {
        return id;
    }

    public AbstractClient getAuthorizedClient() {
        return authorizedClient;
    }

    public void setAuthorizedClient(AbstractClient authorizedClient) {
        this.authorizedClient = authorizedClient;
    }

    public Account getIbanToBeAuthorized() {
        return ibanToBeAuthorized;
    }

    public void setIbanToBeAuthorized(Account ibanToBeAuthorized) {
        this.ibanToBeAuthorized = ibanToBeAuthorized;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isAuthorizationAccepted() {
        return authorizationAccepted;
    }

    public void setAuthorizationAccepted(boolean authorizationAccepted) {
        this.authorizationAccepted = authorizationAccepted;
    }
    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return "ClientAuthorization{" +
                "id=" + id +
                ", authorizedClient=" + authorizedClient +
                ", ibanToBeAuthorized=" + ibanToBeAuthorized +
                ", code=" + code +
                ", authorizationAccepted=" + authorizationAccepted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAuthorization that = (ClientAuthorization) o;
        return Objects.equals(authorizedClient, that.authorizedClient) && Objects.equals(ibanToBeAuthorized, that.ibanToBeAuthorized);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizedClient, ibanToBeAuthorized);
    }
    //endregion
}
