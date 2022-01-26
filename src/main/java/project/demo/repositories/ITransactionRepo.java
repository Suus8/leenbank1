package project.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.demo.model.Transaction;

@RepositoryRestResource
public interface ITransactionRepo extends CrudRepository<Transaction, Long> {

}
