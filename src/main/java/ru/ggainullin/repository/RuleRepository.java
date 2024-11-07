package ru.ggainullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggainullin.entities.ProductType;
import ru.ggainullin.entities.Rule;
import ru.ggainullin.entities.TransactionType;

import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<Rule, UUID> {

    @Query(value = """
            SELECT EXISTS(
                       SELECT 1 FROM transactions t LEFT JOIN products p ON product_id
                       WHERE t.user_id = ? AND p.type = ?)
            """, nativeQuery = true)
    boolean userOf(UUID user_id, ProductType product_type);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            (SELECT COUNT(p.type) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :product_type)>5)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean activeUserOf(UUID user_id, ProductType product_type);

    @Query(value = """
            SELECT SUM(t.amount) FROM transactions t
            LEFT JOIN products p ON
            WHERE t.user_id = ? AND p.product_type = ? AND t.transaction_type = ?
            """, nativeQuery = true)
    int transactionSumCompare(UUID user_id, ProductType product_type, TransactionType transaction_type);

}
