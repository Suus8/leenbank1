/**
 * Created by abuzer.alaca on 19/01/2022
 **/


package project.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.model.PersonDetails;

import java.util.Optional;

@Repository
public interface IPersonDetailRepo extends CrudRepository<PersonDetails, Long> {
    @Query("select p from PersonDetails p where p.id = ?1")
    Optional<PersonDetails> findById(Long personId);
}
