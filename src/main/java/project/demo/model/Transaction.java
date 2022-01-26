package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // withdraw
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    Account accountSender;

    // deposit
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    Account accountReciever;

    Long amountInCents;
    String description;
    String date;

    //range amount

    //endregion

    //region CONSTRUCTOR
    public Transaction(Account debitAccount, Account creditAccount, Long amount, String description, String date) {
        this.accountSender = debitAccount;
        this.accountReciever = creditAccount;
        this.amountInCents = amount;
        this.description = description;
        this.date = date;
    }

    public Transaction(Account debitAccount, Account creditAccount, long amount, String description) {
        this(debitAccount, creditAccount, amount, description, null);
        date = giveCurrentDateAsString();
    }

    //endregion

    //region METHODS
    private String giveCurrentDateAsString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        return formatter.format(currentDate);
    }



    //endregion

    //region GETTERS & SETTERS

    // users input

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountSender() {
        return accountSender;
    }

    public void setAccountSender(Account debitAccount) {
        this.accountSender = debitAccount;
    }

    public Account getAccountReciever() {
        return accountReciever;
    }

    public void setAccountReciever(Account creditAccount) {
        this.accountReciever = creditAccount;
    }

    public Long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Long amount) {
        this.amountInCents = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    //endregion

    //region ToSTRING, HASH, EQUALS, COMPARE

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amountInCents=" + amountInCents +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //endregion
}
