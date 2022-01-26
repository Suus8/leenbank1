package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.AccountDTO;
import project.demo.model.*;

import project.demo.repositories.IAbstractClientRepo;
import project.demo.utility.ClientNameUtility;
import project.demo.view.ClientNumberOfTransactionsView;
import project.demo.repositories.IAccountRepo;
import project.demo.repositories.IClientNumberOfTransactionsRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementReportsService {

    @Autowired
    IAbstractClientRepo abstractClientRepo;

    @Autowired
    IClientNumberOfTransactionsRepo clientNumberOfTransactionRepo;

    @Autowired
    ClientAccountService clientAccountService;

    @Autowired
    IAccountRepo accountRepo;

    public List<AccountDTO> getAverageBalancePerSectorDTOs() {
        List<AccountDTO> averageBalancesDTOs = new ArrayList<>();
        for (Sector sector : Sector.values()) {
            Long averageBalanceOfSector = getAverageBalanceOfSector(sector);
            AccountDTO averageBalance = new AccountDTO(0L, averageBalanceOfSector, "", AccountType.SMALL_BUSINESS, "", sector.toString());
            averageBalancesDTOs.add(averageBalance);
        }
        return averageBalancesDTOs;
    }

    private Long getAverageBalanceOfSector(Sector sector) {
        Double averageBalanceOfSector;
        if (accountRepo.getAverageAccountBalanceBySector(sector.toString()) == null) {
            averageBalanceOfSector = 0.0;
        } else {
            averageBalanceOfSector = accountRepo.getAverageAccountBalanceBySector(sector.toString());
        }
        return Math.round(averageBalanceOfSector);
    }

    public List<AccountDTO> getBalanceTopTen(String accountType) {
        List<AccountDTO> balanceTopTen = new ArrayList<>();
        List<Account> topTenAccounts = accountRepo.getBalanceTopTenAccounts(accountType);
        for (Account account : topTenAccounts)
            clientAccountService.generateAccountDTOs(balanceTopTen, account);
        return balanceTopTen;
    }

    public List<ClientNumberOfTransactions> topTenNumberOfTransactionsPerClient() {
        List<ClientNumberOfTransactionsView> topTen = clientNumberOfTransactionRepo.findTopTenNumberOfTransactionsPerClient();
        List<ClientNumberOfTransactions> topTenWithName = new ArrayList<>();
        for (ClientNumberOfTransactionsView view : topTen) {
            ClientNumberOfTransactions model = new ClientNumberOfTransactions();
            AbstractClient client = abstractClientRepo.findById(view.getClientId()).orElse(null);
            model.setClientId(view.getClientId());
            model.setNumberOfTransactions(view.getNumberOfTransactions());
            model.setClientName(ClientNameUtility.getNameOfAbstractClient(client));
            topTenWithName.add(model);
        }
        return topTenWithName;
    }
}

