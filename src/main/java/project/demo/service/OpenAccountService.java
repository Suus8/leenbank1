package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.model.Account;
import project.demo.model.AccountType;
import project.demo.model.Company;
import project.demo.model.Person;
import project.demo.repositories.IAbstractClientRepo;
import project.demo.repositories.ILoginRepository;

@Service
public class OpenAccountService {
    @Autowired
    IAbstractClientRepo abstractClientRepo;

    @Autowired
    ILoginRepository loginRepo;

    @Autowired
    IAbstractClientRepo clientRepo;

    //region METHODS
    public void createNewConsumerAccount(Person newClient) {
        // TODO: is it needed do do check on SSN/CoC in a cliëntservice/in this service? Or is front end check sufficient?
        newClient.addAccount(new Account(newClient, AccountType.CONSUMER));
        this.abstractClientRepo.save(newClient);
    }

    public void createNewSmallBusinessAccount(Company newClient) {
        newClient.addAccount(new Account(newClient, AccountType.SMALL_BUSINESS));
        this.abstractClientRepo.save(newClient);
    }

    public boolean checkUsername(String username) {
        return this.loginRepo.existsByUsername(username);
    }

    public boolean checkSsn(Long ssn) {
        return clientRepo.existsById(ssn);
    }

    public boolean checkCoC(Long coC) {
        return clientRepo.existsById(coC);
    }
    //endregion
}
