package project.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.demo.model.Account;
import project.demo.model.ClientAuthorization;

@RepositoryRestResource
public interface IClientAuthorization extends CrudRepository<ClientAuthorization, Long> {
    ClientAuthorization findClientAuthorizationByIbanToBeAuthorized(Account account);
}
