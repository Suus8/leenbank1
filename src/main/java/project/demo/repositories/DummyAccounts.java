package project.demo.repositories;

import project.demo.model.AbstractClient;
import project.demo.model.Account;
import project.demo.model.AccountType;
import project.demo.utility.AccountToIbanConverter;

import java.util.ArrayList;
import java.util.List;

public class DummyAccounts {
    //region ATTRIBUTES
    private List<Account> accounts;
    //endregion

    //region CONSTRUCTOR

    public DummyAccounts() {
        this.accounts = new ArrayList<>();

        // Create accounts
        ClientRepo clientRepo = new ClientRepo();
        List<AbstractClient> clientsList = clientRepo.getClients();


        Account account1 = new Account(clientsList.get(0), AccountType.CONSUMER);
        account1.setBalanceInCents(70050L);
        account1.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(12345678));
        accounts.add(account1);
        Account account2 = new Account(clientsList.get(1), AccountType.CONSUMER);
        account2.setBalanceInCents(308050L);
        account2.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(50958749));
        accounts.add(account2);
        Account account3 = new Account(clientsList.get(2), AccountType.CONSUMER);
        account3.setBalanceInCents(80010L);
        account3.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(14504865));
        accounts.add(account3);
        Account account4 = new Account(clientsList.get(3), AccountType.CONSUMER);
        account4.setBalanceInCents(500070L);
        account4.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(458876621));
        accounts.add(account4);
        Account account5 = new Account(clientsList.get(4), AccountType.CONSUMER);
        account5.setBalanceInCents(4080L);
        account5.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654284));
        accounts.add(account5);
        Account account6 = new Account(clientsList.get(5), AccountType.CONSUMER);
        account6.setBalanceInCents(150080L);
        account6.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654285));
        accounts.add(account6);

        // person1 and person2 have another account
        Account account7 = new Account(clientsList.get(6), AccountType.CONSUMER);
        account7.setBalanceInCents(13080L);
        account7.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654286));
        accounts.add(account7);
        Account account8 = new Account(clientsList.get(7), AccountType.CONSUMER);
        account8.setBalanceInCents(25080L);
        account8.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654287));
        accounts.add(account8);


        Account account9 = new Account(clientsList.get(8), AccountType.CONSUMER);
        account9.setBalanceInCents(123080L);
        account9.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654291));
        accounts.add(account9);
        Account account10 = new Account(clientsList.get(9), AccountType.CONSUMER);
        account10.setBalanceInCents(25480L);
        account10.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(33654292));
        accounts.add(account10);
        Account account11 = new Account(clientsList.get(10), AccountType.CONSUMER);
        account11.setBalanceInCents(236480L);
        account11.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654293));
        accounts.add(account11);
        Account account12 = new Account(clientsList.get(11), AccountType.CONSUMER);
        account12.setBalanceInCents(246280L);
        account12.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654294));
        accounts.add(account12);

        // ----- SMALL_BUSINESS ACCOUNTS ------
        Account account13 = new Account(clientsList.get(12), AccountType.SMALL_BUSINESS);
        account13.setBalanceInCents(1002011L);
        account13.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654001));
        accounts.add(account13);
        Account account14 = new Account(clientsList.get(13), AccountType.SMALL_BUSINESS);
        account14.setBalanceInCents(4445305L);
        account14.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654002));
        accounts.add(account14);
        Account account15 = new Account(clientsList.get(14), AccountType.SMALL_BUSINESS);
        account15.setBalanceInCents(3344407L);
        account15.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654003));
        accounts.add(account15);
        Account account16 = new Account(clientsList.get(15), AccountType.SMALL_BUSINESS);
        account16.setBalanceInCents(2244293L);
        account16.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654004));
        accounts.add(account16);
        Account account17 = new Account(clientsList.get(16), AccountType.SMALL_BUSINESS);
        account17.setBalanceInCents(1244293L);
        account17.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654005));
        accounts.add(account17);
        Account account18 = new Account(clientsList.get(17), AccountType.SMALL_BUSINESS);
        account18.setBalanceInCents(5544293L);
        account18.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654006));
        accounts.add(account18);
        Account account19 = new Account(clientsList.get(18), AccountType.SMALL_BUSINESS);
        account19.setBalanceInCents(1244293L);
        account19.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654007));
        accounts.add(account19);
        Account account20 = new Account(clientsList.get(19), AccountType.SMALL_BUSINESS);
        account20.setBalanceInCents(1844293L);
        account20.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654008));
        accounts.add(account20);
        Account account21 = new Account(clientsList.get(20), AccountType.SMALL_BUSINESS);
        account21.setBalanceInCents(2544293L);
        account21.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654009));
        accounts.add(account21);
        Account account22 = new Account(clientsList.get(21), AccountType.SMALL_BUSINESS);
        account22.setBalanceInCents(1223350L);
        account22.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654210));
        accounts.add(account22);
        Account account23 = new Account(clientsList.get(22), AccountType.SMALL_BUSINESS);
        account23.setBalanceInCents(4233350L);
        account23.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654211));
        accounts.add(account23);
        Account account24 = new Account(clientsList.get(23), AccountType.SMALL_BUSINESS);
        account24.setBalanceInCents(1333350L);
        account24.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654212));
        accounts.add(account24);
        Account account25 = new Account(clientsList.get(24), AccountType.SMALL_BUSINESS);
        account25.setBalanceInCents(4423350L);
        account25.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654213));
        accounts.add(account25);
        Account account26 = new Account(clientsList.get(25), AccountType.SMALL_BUSINESS);
        account26.setBalanceInCents(2323350L);
        account26.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654214));
        accounts.add(account26);
        Account account27 = new Account(clientsList.get(26), AccountType.SMALL_BUSINESS);
        account27.setBalanceInCents(2573350L);
        account27.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654215));
        accounts.add(account27);
        Account account28 = new Account(clientsList.get(27), AccountType.SMALL_BUSINESS);
        account28.setBalanceInCents(3283350L);
        account28.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654216));
        accounts.add(account28);
        Account account29 = new Account(clientsList.get(28), AccountType.SMALL_BUSINESS);
        account29.setBalanceInCents(1283350L);
        account29.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654117));
        accounts.add(account29);
        // TWO MORE ACCOUNTS FOR client = clientsList.get(28)
        Account account30 = new Account(clientsList.get(28), AccountType.SMALL_BUSINESS);
        account30.setBalanceInCents(3322223L);
        account30.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654217));
        accounts.add(account30);
        Account account31 = new Account(clientsList.get(28), AccountType.SMALL_BUSINESS);
        account31.setBalanceInCents(5522223L);
        account31.setAccountIBAN(AccountToIbanConverter.convertAccountToIban(133654217));
        accounts.add(account31);


        // --------- AUTHORIZED ACCOUNT  ---------

        account1.addAuthorizedClient(clientsList.get(1));
        account1.addAuthorizedClient(clientsList.get(2));
        account4.addAuthorizedClient(clientsList.get(1));
        account4.addAuthorizedClient(clientsList.get(4));

    }

    //endregion

    //region GETTERS & SETTERS

    public List<Account> getAccounts() {
        return accounts;
    }


    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    //endregion
}
