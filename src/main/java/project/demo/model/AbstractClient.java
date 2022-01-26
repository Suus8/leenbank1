package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.demo.repositories.RepoManager;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractClient {
    //region ATTRIBUTES
    @Id
    @Column(unique = true)
    private Long id; // SSN or CoC depending on type of client

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private ClientDetails clientDetails;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.MERGE)
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(mappedBy = "authorizedClients", cascade = CascadeType.MERGE)
    private Set<Account> authorizedAccounts = new HashSet<>();
    //endregion

    //region CONSTRUCTOR
    public AbstractClient(Long id, Login login, ClientDetails clientDetails) throws IllegalArgumentException {
        // Check if username = unique, is done by DB through login class
        this.id = id;
        this.login = login;
        this.clientDetails = clientDetails;
    }
    //endregion

    //region METHODS
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void addAuthorizedAccount(Account account) {
        this.authorizedAccounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return this.accounts.remove(account);
    }

    public boolean removeAuthorizedAccount(Account account) {
        return this.authorizedAccounts.remove(account);
    }
    //endregion

    //region Helper Methods (private)

    //endregion

    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
            this.accounts = accounts;
    }

    public Set<Account> getAuthorizedAccounts() {
        return authorizedAccounts;
    }

    public void setAuthorizedAccounts(Set<Account> authorizedAccounts) {
        this.authorizedAccounts = authorizedAccounts;
    }


    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    @Override
    public String toString() {
        return "AbstractClient{" +
                "id=" + id +
                ", login=" + login +
                ", clientDetails=" + clientDetails +
                ", accounts=" + accounts +
                ", authorizedAccounts=" + authorizedAccounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractClient that = (AbstractClient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //endregion
}
