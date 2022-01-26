package project.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.demo.model.AbstractClient;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface IAbstractClientRepo extends CrudRepository<AbstractClient, Long> {
    //region Additional Methods
    AbstractClient findByLoginId(Long idByUsername);

    //region Native Query Methods
    @Query(value = "SELECT person_details FROM abstract_client a WHERE id=?1", nativeQuery = true)
    Long findPersonDetailIdById(Long id);

    @Query(value = "SELECT dtype FROM abstract_client a WHERE id=?1", nativeQuery = true)
    String getDtypeByLoginId(Long loginId);
    //endregion
}
