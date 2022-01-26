package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.demo.utility.AccountToIbanConverter;
import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    //region ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountHolder_id", nullable = false)
    private AbstractClient accountHolder;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @OneToMany(mappedBy = "id", cascade = CascadeType.MERGE)
    private List<Transaction> transactionHistory;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "authorized_clients",
            joinColumns = @JoinColumn(name = "account"),
            inverseJoinColumns = @JoinColumn(name = "authorizedClients"))
    private Set<AbstractClient> authorizedClients;

    private static Long lastAccountNumber = 12332121L;
    private String accountIBAN;
    private Long balanceInCents;
    private int pincode;

    @Transient
    private transient final long DEFAULT_START_BALANCE = 0L;
    @Transient
    private transient final int DEFAULT_PINCODE = 12345;
    @Transient
    private transient final int TOP10 = 10;

    //endregion
    //region CONSTRUCTOR
    public Account(AbstractClient accountHolder, AccountType type) {
        this.accountHolder = accountHolder;
        this.pincode = DEFAULT_PINCODE;
        this.accountIBAN = generateIBAN();
        this.balanceInCents = DEFAULT_START_BALANCE;
        this.type = type;
        this.transactionHistory = new ArrayList<>();
        this.authorizedClients = new HashSet<>();
    }
    //endregion

    //region METHODS
    private String generateIBAN(){
        String tempIBAN = AccountToIbanConverter.convertAccountToIban(lastAccountNumber);

        this.lastAccountNumber++;

        return tempIBAN;
    }

    public void addAuthorizedClient(AbstractClient authorizedClient) {
        this.authorizedClients.add(authorizedClient);
        authorizedClient.addAccount(this);
    }

    public void removeAuthorizedClient(AbstractClient authorizedClient) {
        this.authorizedClients.remove(authorizedClient);
        authorizedClient.removeAccount(this);
    }

    public void addTransactionToTransactionHistory(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }


    // get/generate the 10 recent transaction from the transactionHistory
    // TODO does this need to be put in a Service? (not in model class)
    public List<Transaction> get10RecentTransaction() {
        List<Transaction> recent10Transaction;
        // if length of the list is smaller than 10
        if (this.transactionHistory.size() <= TOP10) {
            recent10Transaction = this.transactionHistory;
            return recent10Transaction;
        }
        // if transaction history length is bigger than 10
        else {
            int start = transactionHistory.size() - TOP10;
            recent10Transaction = this.transactionHistory.subList(start, (transactionHistory.size() + 1));
            return recent10Transaction;
        }
    }

    //endregion

    //region Helper Methods
    public void subtractFromBalance(Long amount) {
        balanceInCents -= amount;
        System.out.println("Your account balance is now € " + balanceInCents + ".");
    }

    // withdraw money from account
    public void addToBalance(Long amount) {
        balanceInCents += amount;
        System.out.println(amount + " is added to your balance. Your balance is " + balanceInCents + " cents");
    }

    //endregion

    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractClient getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AbstractClient accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountNumber) {
        this.accountIBAN = accountNumber;
    }

    public Long getBalanceInCents() {
        return balanceInCents;
    }

    public void setBalanceInCents(Long balance) {
        this.balanceInCents = balance;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Set<AbstractClient> getAuthorizedClients() {
        return authorizedClients;
    }

    public void setAuthorizedClients(Set<AbstractClient> authorizedClients) {
        this.authorizedClients = authorizedClients;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountIBAN, account.accountIBAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIBAN);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", accountIBAN='" + accountIBAN + '\'' +
                ", balanceInCents=" + balanceInCents +
                ", pincode=" + pincode +
                '}';
    }

//endregion
}
