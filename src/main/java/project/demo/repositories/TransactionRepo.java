package project.demo.repositories;

import project.demo.model.Account;
import project.demo.model.Transaction;

import java.util.ArrayList;
import java.util.List;


public class TransactionRepo {
    //region ATTRIBUTES
    private List<Transaction> transactions;

    //endregion

    //region CONSTRUCTOR
    public TransactionRepo() {
        this.transactions = new ArrayList<>();
    }

    public void loadTransactions(List<Account> accountList) {
        Transaction transaction = new Transaction(accountList.get(0), accountList.get(1), 5000L, "account1 to account2", "2021-05-15");
        transactions.add(transaction);
        Transaction transaction2 = new Transaction(accountList.get(12), accountList.get(14), 3000L, "account13 to account15", "2021-04-01");
        transactions.add(transaction2);
        Transaction transaction3 = new Transaction(accountList.get(2), accountList.get(3), 2540L, "account3 to account4", "2021-06-20");
        transactions.add(transaction3);
        Transaction transaction4 = new Transaction(accountList.get(1), accountList.get(3), 50000L, "account2 to account4", "2021-08-05");
        transactions.add(transaction4);
        Transaction transaction5 = new Transaction(accountList.get(14), accountList.get(12), 4613L, "account15 to account13", "2021-07-04");
        transactions.add(transaction5);
        Transaction transaction6 = new Transaction(accountList.get(1), accountList.get(0), 2099L, "account2 to account1", "2021-09-30");
        transactions.add(transaction6);

        // SMALL_BUSINESS TRANSACTIONS FOR TESTING NUMBER OF TRANSACTIONS
        Transaction transaction7 = new Transaction(accountList.get(28), accountList.get(12), 100000L, "account29 to account13", "2021-09-30");
        transactions.add(transaction7);
        Transaction transaction8 = new Transaction(accountList.get(27), accountList.get(13), 500000L, "account28 to account14", "2021-09-30");
        transactions.add(transaction8);
        Transaction transaction9 = new Transaction(accountList.get(29), accountList.get(13), 250000L, "account30 to account14", "2021-09-30");
        transactions.add(transaction9);
        Transaction transaction10 = new Transaction(accountList.get(29), accountList.get(13), 250000L, "account30 to account14", "2021-09-30");
        transactions.add(transaction10);
        Transaction transaction11 = new Transaction(accountList.get(29), accountList.get(10), 10000L, "account30 to account11", "2021-09-30");
        transactions.add(transaction11);
        Transaction transaction12 = new Transaction(accountList.get(29), accountList.get(9), 10000L, "account30 to account10", "2021-09-30");
        transactions.add(transaction12);
        Transaction transaction13 = new Transaction(accountList.get(29), accountList.get(9), 10000L, "account30 to account10", "2021-09-30");
        transactions.add(transaction13);
        Transaction transaction14 = new Transaction(accountList.get(28), accountList.get(6), 10000L, "account29 to account7", "2021-09-30");
        transactions.add(transaction14);
        Transaction transaction15 = new Transaction(accountList.get(28), accountList.get(7), 10000L, "account29 to account8", "2021-09-30");
        transactions.add(transaction15);
        Transaction transaction16 = new Transaction(accountList.get(28), accountList.get(9), 10000L, "account29 to account10", "2021-09-30");
        transactions.add(transaction16);
        Transaction transaction17 = new Transaction(accountList.get(28), accountList.get(10), 10000L, "account29 to account11", "2021-09-30");
        transactions.add(transaction17);
        Transaction transaction18 = new Transaction(accountList.get(28), accountList.get(11), 10000L, "account29 to account12", "2021-09-30");
        transactions.add(transaction18);
        Transaction transaction19 = new Transaction(accountList.get(28), accountList.get(12), 10000L, "account29 to account13", "2021-09-30");
        transactions.add(transaction19);
        Transaction transaction20 = new Transaction(accountList.get(26), accountList.get(13), 10000L, "account27 to account14", "2021-09-30");
        transactions.add(transaction20);
        Transaction transaction21 = new Transaction(accountList.get(26), accountList.get(14), 10000L, "account27 to account15", "2021-09-30");
        transactions.add(transaction21);
        Transaction transaction22 = new Transaction(accountList.get(26), accountList.get(27), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction22);
        Transaction transaction23 = new Transaction(accountList.get(26), accountList.get(27), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction23);
        Transaction transaction24 = new Transaction(accountList.get(26), accountList.get(13), 10000L, "account27 to account14", "2021-09-30");
        transactions.add(transaction24);
        Transaction transaction25 = new Transaction(accountList.get(26), accountList.get(14), 10000L, "account27 to account15", "2021-09-30");
        transactions.add(transaction25);
        Transaction transaction26 = new Transaction(accountList.get(26), accountList.get(27), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction26);
        Transaction transaction27 = new Transaction(accountList.get(26), accountList.get(27), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction27);
        Transaction transaction28 = new Transaction(accountList.get(25), accountList.get(24), 10000L, "account27 to account14", "2021-09-30");
        transactions.add(transaction28);
        Transaction transaction29 = new Transaction(accountList.get(25), accountList.get(24), 10000L, "account27 to account15", "2021-09-30");
        transactions.add(transaction29);
        Transaction transaction30 = new Transaction(accountList.get(25), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction30);
        Transaction transaction31 = new Transaction(accountList.get(25), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction31);
        Transaction transaction32 = new Transaction(accountList.get(25), accountList.get(24), 10000L, "account27 to account14", "2021-09-30");
        transactions.add(transaction32);
        Transaction transaction33 = new Transaction(accountList.get(23), accountList.get(24), 10000L, "account27 to account15", "2021-09-30");
        transactions.add(transaction33);
        Transaction transaction34 = new Transaction(accountList.get(23), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction34);
        Transaction transaction35 = new Transaction(accountList.get(23), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction35);
        Transaction transaction36 = new Transaction(accountList.get(23), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction36);
        Transaction transaction37 = new Transaction(accountList.get(23), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction37);
        Transaction transaction38 = new Transaction(accountList.get(22), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction38);
        Transaction transaction39 = new Transaction(accountList.get(22), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction39);
        Transaction transaction40 = new Transaction(accountList.get(22), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction40);
        Transaction transaction41 = new Transaction(accountList.get(22), accountList.get(24), 10000L, "account27 to account28", "2021-09-30");
        transactions.add(transaction41);

    }

    //endregion

    //region GETTERS & SETTERS

    public List<Transaction> getTransactions() {
        return transactions;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    //endregion
}
