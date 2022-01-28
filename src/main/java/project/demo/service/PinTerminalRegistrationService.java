package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.model.AbstractClient;
import project.demo.model.Account;
import project.demo.model.Company;
import project.demo.repositories.IAbstractClientRepo;
import project.demo.repositories.IAccountRepo;

@Service
public class PinTerminalRegistrationService {

    @Autowired
    IAccountRepo accountRepo;

    @Autowired
    IAbstractClientRepo abstractClientRepo;

    public String registerPinTerminal(String accountIban) {
        Account account = accountRepo.getAccountByAccountIBAN(accountIban);
        AbstractClient abstractClient = abstractClientRepo.getAbstractClientByAccountsIsContaining(account);
        if (abstractClient instanceof Company) {
            Company company = (Company) abstractClient;
            int pinCode = generatePinTerminalID();
            company.setRegistrationCodePinTerminal(pinCode);
            return String.format("De pinterminal is geregistreerd met code: %d", pinCode);
        } else return String.format("Registratie niet mogelijk. %s hoort niet bij een bedrijf.", accountIban);
    }

    public int generatePinTerminalID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
