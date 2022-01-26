package project.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import project.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.demo.repositories.*;

import java.util.List;
import java.util.Optional;

@EntityScan("project.demo.model")
@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication implements CommandLineRunner {

    @Autowired
    public IAbstractClientRepo clientRepoService;

    @Autowired
    public IAccountRepo accountRepoService;

    @Autowired
    public ITransactionRepo transactionRepoService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Load data into DB

        ClientRepo clientRepo = new ClientRepo();
        clientRepoService.saveAll(clientRepo.getClients());

        DummyAccounts accountRepo = new DummyAccounts();
        accountRepoService.saveAll(accountRepo.getAccounts());

        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.loadTransactions(accountRepo.getAccounts());
        transactionRepoService.saveAll(transactionRepo.getTransactions());

    }

    // @Jelle: In controller (deze methode wordt aangeroepen door javascript)
    public Iterable<Account> getAllAccounts() {
        return accountRepoService.findAll();
    }

    public Iterable<AbstractClient> getAllClients() {
        return clientRepoService.findAll();
    }


}

