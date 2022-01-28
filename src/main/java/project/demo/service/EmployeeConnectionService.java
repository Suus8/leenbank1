package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.ConnectionTerminalDTO;
import project.demo.dto.EmployeeConnectionResultDTO;
import project.demo.dto.TerminalConnectionResultDTO;
import project.demo.model.Account;
import project.demo.model.Company;
import project.demo.repositories.IAccountRepo;

import java.util.List;

@Service
public class EmployeeConnectionService {

    @Autowired
    IAccountRepo accountRepo;
    Account accountCashier;
    ConnectionTerminalDTO connectionTerminalDataDTO;
    List<Account> allAccounts;
    Company company;

    

    public EmployeeConnectionResultDTO processConnectionRequest(ConnectionTerminalDTO connectionrequest) {
        loadAccounts();
        findAccountByIban(connectionTerminalDataDTO);


        EmployeeConnectionResultDTO result = new EmployeeConnectionResultDTO(getFiveDigidValidationCode());
        resetService();
        return result;
    }

    private void loadAccounts() {
        allAccounts = (List<Account>) accountRepo.findAll();
    }

    private void findAccountByIban(ConnectionTerminalDTO connectionTerminalDataDTO) {
        this.connectionTerminalDataDTO = connectionTerminalDataDTO;
        accountCashier = giveAccountForIban(connectionTerminalDataDTO.getAccountNumberCashier());
    }

    private Account giveAccountForIban(String iban) {
        for (Account a : allAccounts) {
            if (a.getAccountIBAN().equals(iban)) return a;
        }
        return null;
    }

    // 5digid code word aangemaakt en toegevoegd aan account
    public int getFiveDigidValidationCode() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;

        int randomValidationCode = (int) (Math.random() * range) + min;
        return randomValidationCode;
    }

    //TODO After new parameter for CompanyAccount
//    public void addValidationCodeToCompanyAccount() {
//        getFiveDigidValidationCode();
//    }

    // Daarna return validatiecode

    private void resetService() {
        connectionTerminalDataDTO = null;
        accountCashier = null;
        allAccounts = null;
    }

}
