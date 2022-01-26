package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.AccountDTO;
import project.demo.model.*;
import project.demo.repositories.IAccountRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientAccountService {

    @Autowired
    IAccountRepo accountRepo;

    private List<AccountDTO> allAccountDTOs;

    public ClientAccountService() {
        allAccountDTOs = new ArrayList<>();
    }

    public void loadAllAccountDTOs() {
        List<AccountDTO> loadedAccountDTOs = new ArrayList<>();
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();
        for (Account account : allAccounts
        ) {
            generateAccountDTOs(loadedAccountDTOs, account);
        }
        allAccountDTOs = loadedAccountDTOs;
    }

    public void generateAccountDTOs(List<AccountDTO> accountsDTOs, Account account) {
        String name = retrieveName(account);
        Long id = account.getId();
        Long balance = account.getBalanceInCents();
        String accountIBAN = account.getAccountIBAN();
        AccountType type = account.getType();
        String companySector = retrieveCompanySector(account);
        AccountDTO row = new AccountDTO(id, balance, accountIBAN, type, name, companySector);
        accountsDTOs.add(row);
    }

    private String retrieveName(Account account) {
        String name;
        if (account.getAccountHolder() instanceof Person) {
            name = ((Person) (account.getAccountHolder())).getPersonDetails().getFullName().getFullNameAsString();
        } else if (account.getAccountHolder() instanceof Company) {
            name = ((Company) (account.getAccountHolder())).getCompanyDetails().getName();
        } else {
            name = "naam onbekend";
        }
        return name;
    }

    private String retrieveCompanySector(Account account) {
        String sector;
        if (account.getAccountHolder() instanceof Company) {
            sector = ((Company) (account.getAccountHolder())).getCompanyDetails().getSector().toString();
        } else {
            sector = "n.v.t.";
        }
        return sector;
    }

    public List<AccountDTO> getAllAccountDTOs() {
        return allAccountDTOs;
    }

    public List<AccountDTO> getConsumerAccountDTOs() {
        List<AccountDTO> consumerAccountDTOs = new ArrayList<>();
        for (AccountDTO accountDTO : allAccountDTOs
        ) {
            if (accountDTO.getAccountType() == AccountType.CONSUMER) {
                consumerAccountDTOs.add(accountDTO);
            }
        }
        return consumerAccountDTOs;
    }

}
