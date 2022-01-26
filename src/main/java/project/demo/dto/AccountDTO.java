package project.demo.dto;

import project.demo.model.AccountType;

import java.util.Objects;

public class AccountDTO implements Comparable<AccountDTO> {
    Long accountId;
    Long accountBalanceInCents;
    String accountIBAN;
    AccountType accountType;
    String clientName;
    String companySector;

    public AccountDTO(Long accountId, Long accountBalance, String accountIBAN, AccountType accountType, String clientName, String companySector) {
        this.accountId = accountId;
        this.accountBalanceInCents = accountBalance;
        this.accountIBAN = accountIBAN;
        this.accountType = accountType;
        this.clientName = clientName;
        this.companySector = companySector;
    }

//region getters

    public Long getAccountId() {
        return accountId;
    }

    public Long getAccountBalanceInCents() {
        return accountBalanceInCents;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCompanySector() {
        return companySector;
    }


    //endregion

    public int compareTo(AccountDTO o) {
        return Long.compare(this.accountId, o.accountId);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", accountBalanceInCents=" + accountBalanceInCents +
                ", accountIBAN='" + accountIBAN + '\'' +
                ", accountType=" + accountType +
                ", clientName='" + clientName + '\'' +
                ", companySector='" + companySector + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(accountBalanceInCents, that.accountBalanceInCents) && Objects.equals(accountIBAN, that.accountIBAN) && accountType == that.accountType && Objects.equals(clientName, that.clientName) && Objects.equals(companySector, that.companySector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountBalanceInCents, accountIBAN, accountType, clientName, companySector);
    }
}