package project.demo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.demo.model.Account;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface IAccountRepo extends CrudRepository<Account, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE `leenmantest`.`account` SET `balance_in_cents` = ?1 WHERE (`id` = ?2)", nativeQuery = true)
    void setNewBalanceByAccountID(Long newBalance, Long id);

    Account getAccountsByAccountIBANIs(String iban);

    @Query(value = "SELECT id FROM leenmantest.account where account_holder_id = ?1", nativeQuery = true)
    List<Long> getAccountIDWhereClientIsOwner(Long id);

    @Query(value = "SELECT account FROM leenmantest.authorized_clients where authorized_clients = ?1", nativeQuery = true)
    List<Long> getAccountIDWhereClientIsAuthorized(Long id);

    Account getAccountByAccountIBAN(String accountIBAN);

    List<Account> getAccountsByIdIsIn(List<Long> ids);

    @Query(value = "SELECT AVG(a.balance_in_cents)\n" +
            "FROM account a, abstract_client ac, company_details cd\n" +
            "WHERE a.account_holder_id = ac.id\n" +
            "AND cd.id = ac.company_details\n" +
            "AND cd.sector = ?1",
            nativeQuery = true)
    Double getAverageAccountBalanceBySector(String sector);

    @Query(value = "SELECT * FROM account WHERE type = ?1 ORDER BY balance_in_cents DESC LIMIT 10", nativeQuery = true)
    List<Account> getBalanceTopTenAccounts(String accountType);

    boolean existsAccountByAccountIBAN(String accountIBAN);

}