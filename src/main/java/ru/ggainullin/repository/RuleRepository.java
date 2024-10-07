package ru.ggainullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggainullin.entities.Rule;

import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<Rule, UUID> {

    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean userOf(UUID user_id, String type);

    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 0 from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type)
            THEN 0 ELSE 1 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean notUserOf(UUID user_id, String type);

    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND t.type = 'DEPOSIT'
            AND t.amount = :amount
            AND p.type = :type)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean topUp(UUID user_id, Integer amount, String type);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            (SELECT SUM(amount) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type
            AND t.type = 'DEPOSIT')>(SELECT SUM(amount) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type
            AND t.type = 'WITHDRAWAL'))
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean topUpGTSpend(UUID user_id, String type);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            (SELECT SUM(amount) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type
            AND t.type = 'WITHDRAWAL')>:amount)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean spendSGT(UUID user_id, String type, Integer amount);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            (SELECT SUM(amount) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type
            AND t.type = 'DEPOSIT')>:amount)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean topUpSGT(UUID user_id, String type, Integer amount);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            (SELECT COUNT(p.type) from transactions t
            JOIN products p
            ON t.product_id = p.id
            WHERE t.user_id = :user_id
            AND p.type = :type)>10)
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean activeUserOf(UUID user_id, String type);
    
}
