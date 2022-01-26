package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.demo.dto.AccountDTO;
import project.demo.model.ClientNumberOfTransactions;
import project.demo.service.ClientAccountService;
import project.demo.service.ManagementReportsService;

import java.util.List;


@RestController
public class ManagementReportsDashboardController {

    @Autowired
    ClientAccountService clientAccountService;

    @Autowired
    ManagementReportsService managementReportsService;

    @RequestMapping(value = "/allAccounts", method = RequestMethod.GET)
    public List<AccountDTO> getAllAccountsDetails() {
        clientAccountService.loadAllAccountDTOs();
        return clientAccountService.getAllAccountDTOs();
    }

    @RequestMapping(value = "/averageBalancePerSector", method = RequestMethod.GET)
    public List<AccountDTO> getAverageBalancePerSector() {
        return managementReportsService.getAverageBalancePerSectorDTOs();
    }

    @RequestMapping(value = "/topTenBalance/{accountType}", method = RequestMethod.GET)
    public List<AccountDTO> getTopTenBalance(@PathVariable String accountType) {
        return managementReportsService.getBalanceTopTen(accountType);
    }

    @RequestMapping(value = "/topTenNumberOfTransactions", method = RequestMethod.GET)
    public List<ClientNumberOfTransactions> getTopTenNumberOfTransactionsPerClient() {
        return managementReportsService.topTenNumberOfTransactionsPerClient();
    }

}
