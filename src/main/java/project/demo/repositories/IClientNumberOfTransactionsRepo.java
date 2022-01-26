package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.demo.model.ClientNumberOfTransactions;
import project.demo.view.ClientNumberOfTransactionsView;

import java.util.List;


@Repository
public interface IClientNumberOfTransactionsRepo extends JpaRepository<ClientNumberOfTransactions, Long> {

    @Query(value = "SELECT total.account_holder_id as clientId, SUM(total.number_of_transactions) as numberOfTransactions\n" +
            "FROM\n" +
            "(SELECT a2.ahid AS account_holder_id, aint.number_of_transactions\n" +
            "            FROM\n" +
            "       (SELECT \n" +
            "                t.id AS account_id, SUM(c) AS number_of_transactions\n" +
            "             FROM\n" +
            "        ((SELECT\n" +
            "            t2.account_reciever_id AS id, COUNT(t2.account_reciever_id) AS c  FROM  leenmantest.transaction t2\n" +
            "           GROUP BY t2.account_reciever_id)UNION ALL (SELECT\n" +
            "           t.account_sender_id AS id, COUNT(t.account_sender_id) AS c\n" +
            "          FROM leenmantest.transaction t\n" +
            "         GROUP BY t.account_sender_id)) t GROUP BY t.id) aint\n" +
            "              INNER JOIN\n" +
            "             (SELECT   a.account_holder_id AS ahid, a.id AS aid  FROM   leenmantest.account a) a2\n" +
            "         ON a2.aid = aint.account_id) total\n" +
            "       GROUP BY clientId  ORDER BY numberOfTransactions DESC LIMIT 10", nativeQuery = true)
    List<ClientNumberOfTransactionsView> findTopTenNumberOfTransactionsPerClient();

}
