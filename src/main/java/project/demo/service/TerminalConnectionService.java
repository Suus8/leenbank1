package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.ConnectionTerminalDTO;
import project.demo.dto.TerminalConnectionResultDTO;
import project.demo.dto.ConnectionStatusCode;
import project.demo.model.Account;
import project.demo.repositories.IAccountRepo;

import java.util.List;

@Service
public class TerminalConnectionService {

    @Autowired
    IAccountRepo accountRepo;
    int statuscode;
    Account accountCashier;
    ConnectionTerminalDTO connectionTerminalDataDTO;
    List<Account> allAccounts;

    public TerminalConnectionResultDTO processConnectionRequest(ConnectionTerminalDTO connectionrequest) {
        loadAccounts();
        findAccountByIban(connectionTerminalDataDTO);
        validateIBanAndValidationCode();

        boolean connectionComplete = (statuscode == ConnectionStatusCode.CODE_CONNECTION_PROCESSED);
        TerminalConnectionResultDTO result = new TerminalConnectionResultDTO(connectionComplete, statuscode, getPinterminalID());
        resetService();
        return result;
    }

    private void loadAccounts() {
        allAccounts = (List<Account>) accountRepo.findAll();
    }

    // todo exception handling for wrong accountnumber with statuscode
    public void findAccountByIban(ConnectionTerminalDTO connectionTerminalDataDTO) {
        this.connectionTerminalDataDTO = connectionTerminalDataDTO;
        accountCashier = giveAccountForIban(connectionTerminalDataDTO.getAccountNumberCashier());
    }

    private Account giveAccountForIban(String iban) {
        for (Account a : allAccounts) {
            if (a.getAccountIBAN().equals(iban)) return a;
        }
        return null;
    }

 //TODO: ValidationCode check: Add another parameter to CompanyAccount
    public void validateIBanAndValidationCode() {
        if (giveAccountForIban(connectionTerminalDataDTO.getAccountNumberCashier()) == null)
            statuscode = ConnectionStatusCode.CODE_IBAN_NOT_FOUND;
//        else if (accountCashier.getValidationCode() != connectionDataDTO.getConnectionCode())
//            statuscode = ConnectionStatusCode.CODE_VALIDATION_CODE_NOT_CORRECT;
        else statuscode = ConnectionStatusCode.CODE_CONNECTION_PROCESSED;
    }


//    // Matching 5 digitcode and return updated Statuscode
//    // todo create Account field ValidationCode for all CompanyAccounts
////    private void verifyValidationCode() {
//        if (accountCashier != null) {
//            if (
//        } return ;
//    }

    public int generatePinTerminalID() {
        int min = 10000000;
        int max = 99999999;
        int range = max - min + 1;

        int generatedPinTerminal = (int) (Math.random() * range) + min;
        return generatedPinTerminal;
    }

    public int getPinterminalID() {
        if (statuscode == ConnectionStatusCode.CODE_CONNECTION_PROCESSED) {
//            addPinterminalIDToCompanyAccount();
            return generatePinTerminalID();
        }
        return 0;
    }

    //TODO: Adding another parameter for companyAccount and database
//    public void addPinterminalIDToCompanyAccount(){
//        Account./
//    }

    private void resetService() {
        connectionTerminalDataDTO = null;
        accountCashier = null;
        allAccounts = null;
        statuscode = 0;
    }
}
