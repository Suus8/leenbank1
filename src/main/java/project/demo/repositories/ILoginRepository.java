/**
 * Created by abuzer.alaca on 17/01/2022
 **/


package project.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.demo.model.Login;

@Repository
public interface ILoginRepository extends CrudRepository<Login, Long> {
    boolean existsByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT id FROM login WHERE username = ?1", nativeQuery = true)
    Long getIdByUsername(String username);


    /**
     * Method to check wheater a username is already stored in DB/used by another client
     *
     * @param username Username that the new client wants to use
     * @return Return true is username is already used (and thus NOT available)
     */
    boolean existsByUsername(String username);
}
