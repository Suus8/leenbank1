package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.AccountDTO;
import project.demo.dto.ClientAuthorizationDTO;
import project.demo.model.AbstractClient;
import project.demo.model.Account;
import project.demo.model.ClientAuthorization;
import project.demo.model.Company;
import project.demo.repositories.IAbstractClientRepo;
import project.demo.repositories.IAccountRepo;
import project.demo.repositories.IClientAuthorization;
import project.demo.repositories.ILoginRepository;
import project.demo.utility.AccountToIbanConverter;
import project.demo.utility.ClientNameUtility;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    IAccountRepo accountRepo;

    @Autowired
    ILoginRepository loginRepo;

    @Autowired
    IAbstractClientRepo clientRepo;

    @Autowired
    IClientAuthorization clientAuthorizationRepo;

    //region METHODS
    public String getClientNameByUsername(String username) {
        // Get key of login object in DB
        Long key = loginRepo.getIdByUsername(username);

        // Get client name with key
        AbstractClient client = clientRepo.findByLoginId(key);
        return ClientNameUtility.getNameOfAbstractClient(client);
    }

    public String authorizeThirdParty(ClientAuthorizationDTO dto) {
        try {
            // Get client, account & code from dto
            AbstractClient authorizedClient = clientRepo.findByLoginId(loginRepo.getIdByUsername(dto.getUsername()));
            Account accountToBeAuthorized = accountRepo.getAccountByAccountIBAN(dto.getIban());
            int code = dto.getCode();

            // Create clientAuthorization + save in DB + get id
            ClientAuthorization clientAuthorization = new ClientAuthorization(authorizedClient, accountToBeAuthorized, code);
            Long authorizationId = clientAuthorizationRepo.save(clientAuthorization).getId();

            // Return message when succeeded
            return returnMessageIfAuthorizationIsSuccessful(authorizationId, clientAuthorization);
        } catch (NullPointerException npe) {
            // Return message when null pointer exception occurs
            return "Autorisatieverzoek mislukt. Probeer het opnieuw.";
        }
    }

    public String acceptAuthorizationForAccount(ClientAuthorizationDTO dto) {
        // Get iban from account number + account from iban
        String iban = AccountToIbanConverter.convertAccountToIban(dto.getIban());
        Account account = accountRepo.getAccountByAccountIBAN(iban);

        // Check if authorization is still active & if details are correct
        ClientAuthorization clientAuthorization = clientAuthorizationRepo.findClientAuthorizationByIbanToBeAuthorized(account);
        if (checkDTODetails(dto, clientAuthorization)) {
            // If so, accept authorisation and set details
            clientAuthorization.setAuthorizationAccepted(true);
            clientAuthorization.getAuthorizedClient().addAuthorizedAccount(account);
            clientAuthorization.getIbanToBeAuthorized().addAuthorizedClient(clientAuthorization.getAuthorizedClient());
            // Save details to DB
            clientAuthorization = clientAuthorizationRepo.save(clientAuthorization);
            // Return message when succeeded or not
            return returnMessageIfAuthorizationIsAcceptedSuccessfully(clientAuthorization);
        } else {
            return "Het is niet gelukt om het autorisatieverzoek te accepteren. Probeer het opnieuw.";
        }


        // TODO
    }

    public void setNewBalanceForAccount(Long amount, Long id) {
        accountRepo.setNewBalanceByAccountID(amount, id);
    }

    public AccountDTO getAccountByIban(String iban) {
        Account account = accountRepo.getAccountsByAccountIBANIs(iban);
        if (account == null) return null;
        return new AccountDTO(account.getId(), account.getBalanceInCents(), account.getAccountIBAN(), account.getType(), ClientNameUtility.getNameOfAbstractClientOfAccount(account), retrieveCompanySector(account));
    }

    public List<AccountDTO> getListOfAccountDTOOwned(Long id) {
        List<Long> accountIds = accountRepo.getAccountIDWhereClientIsOwner(id);
        if (accountIds.isEmpty()) return null;
        return addAccountDTOsToList(accountIds);
    }

    private List<AccountDTO> addAccountDTOsToList(List<Long> accountIds) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<Account> accounts = accountRepo.getAccountsByIdIsIn(accountIds);
        for (Account acc : accounts) {
            AccountDTO accountDTO = new AccountDTO(acc.getId(), acc.getBalanceInCents(), acc.getAccountIBAN(), acc.getType(), ClientNameUtility.getNameOfAbstractClientOfAccount(acc), retrieveCompanySector(acc));
            accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }

    public List<AccountDTO> getAllAuthorizedAccountDTOs(Long id) {
        List<Long> accountIds = accountRepo.getAccountIDWhereClientIsAuthorized(id);
        if (accountIds.isEmpty()) return null;
        return addAccountDTOsToList(accountIds);
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

    public boolean checkIfIBANexists(String iban) {
        return accountRepo.existsAccountByAccountIBAN(iban);
    }

    public boolean checkIfSufficientFunds(Long fundsInEuro, String iban) {
        Account account = accountRepo.getAccountByAccountIBAN(iban);
        return ((account.getBalanceInCents() / 100) >= fundsInEuro);
    }

    //endregion

    //region Helper Methods
    private boolean checkDTODetails(ClientAuthorizationDTO dto, ClientAuthorization clientAuthorization) {
        boolean iban = (AccountToIbanConverter.convertAccountToIban(dto.getIban()).equals(clientAuthorization.getIbanToBeAuthorized().getAccountIBAN()));
        boolean user = (dto.getUsername().equals(clientAuthorization.getAuthorizedClient().getLogin().getUsername()));
        boolean code = (dto.getCode() == clientAuthorization.getCode());

        return (iban && user && code);
    }

    private String returnMessageIfAuthorizationIsSuccessful(Long authorizationId, ClientAuthorization clientAuthorization) {
        String iban = clientAuthorization.getIbanToBeAuthorized().getAccountIBAN();
        String clientName = ClientNameUtility.getNameOfAbstractClient(clientAuthorization.getAuthorizedClient());
        int code = clientAuthorization.getCode();

        return "Autorisatieverzoek verstuurd met autorisatienummer: " + authorizationId + ". Dit verzoek is 24 uur " +
                "geldig. Na het verlopen van ervan dient een nieuw verzoek gedaan te worden." +
                "\n\nAutorisatiedetails:" +
                "\n* Geautoriseerde rekening: " + iban + "." +
                "\n* Gemachtigde rekeninghouder tot dit account: " + clientName + "." +
                "\n* Autorisatiecode: " + code +
                "\n\n Geef bovenstaande code aan de gemachtigde rekeninghouder. Deze dient het verzoek tot autorisatie " +
                "binnen 24 uur op de eigen account te bevestigen. Dit kan via de knop \"Koppel nieuwe rekening\" op het" +
                "dashboard. Er wordt dan gevraagd om bovenstaande code in te voeren ter bevestiging.";
    }

    private String returnMessageIfAuthorizationIsAcceptedSuccessfully(ClientAuthorization clientAuthorization) {
        Long id = clientAuthorization.getId();
        String iban = clientAuthorization.getIbanToBeAuthorized().getAccountIBAN();
        String clientName = ClientNameUtility.getNameOfAbstractClient(clientAuthorization.getAuthorizedClient());

        return "Autorisatieverzoek met autorisatienummer " + id + " is succesvol afgehandeld." +
                "\n\nAutorisatiedetails:" +
                "\n* Geautoriseerde rekening: " + iban + "." +
                "\n* Gemachtigde rekeninghouder tot dit account: " + clientName + "." +
                "\n\n Wanneer u terugkeert naar uw dashboard, staat dit account tussen de de rekeningen waartoe u " +
                "toegang heeft. U kunt op deze rekeningen alleen geld overschrijven of details inzien. Het verwijderen " +
                "van een rekening waartoe u gemachtigd bent, is niet mogelijk.";
    }


    //endregion
}


