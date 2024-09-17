package ru.ggainullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggainullin.entities.Transaction;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            JOIN users u
            ON t.user_id = u.id
            WHERE u.id = :user_id
            AND p.type = 'DEBIT')
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean ifUserHasDebitAccount(UUID user_id);
    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            JOIN users u
            ON t.user_id = u.id
            WHERE u.id = :user_id
            AND p.type = 'INVEST')
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean ifUserHasInvestAccount(UUID user_id);
    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            JOIN users u
            ON t.user_id = u.id
            WHERE u.id = :user_id
            AND p.type = 'CREDIT')
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean ifUserHasCreditAccount(UUID user_id);
    @Query(value = """
            SELECT CAST(
            CASE WHEN EXISTS(
            SELECT 1 from transactions t
            JOIN products p
            ON t.product_id = p.id
            JOIN users u
            ON t.user_id = u.id
            WHERE u.id = :user_id
            AND p.type = 'SAVING'
            AND t.amount < 1000
            AND t.type = 'DEPOSIT')
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
             """, nativeQuery = true)
    boolean isDepositToSavingAccountLessThanOneThousand(UUID user_id);
    @Query(value = """
            SELECT count (t.type)
            FROM transactions t
            JOIN users u
            ON t.user_id = u.id
            JOIN products p
            ON t.product_id = p.id
            WHERE t.amount > 10000
            AND u.id = :user_id
            AND p.type = 'SAVING'
            OR p.type = 'DEBIT'
            AND t.type = 'DEPOSIT'
             """, nativeQuery = true)
    int quantityOfDepositToSavingOrDebitAccount(UUID user_id);
    @Query(value = """
            SELECT
            SUM(t.amount) AS total
            FROM
            transactions t
            JOIN
            product p ON t.product_id = p.id
            WHERE
            t.user_id = :user_id
            AND t.type = 'DEPOSIT'
            AND p.type = 'DEBIT'
            """, nativeQuery = true)
    Integer totalAmountOfDepositToDebitAccount(UUID user_id);

    @Query(value = """
            SELECT
            SUM(t.amount) AS total
            FROM
            transactions t
            JOIN
            product p ON t.product_id = p.id
            WHERE
            t.user_id = :user_id
            AND t.type = 'WITHDRAWAL'
            AND p.type = 'DEBIT'
            """, nativeQuery = true)
    Integer totalAmountOfWithdrawalFromDebitAccount(UUID user_id);

    @Query(value = """
            SELECT CAST(
            CASE WHEN (
            SELECT SUM(t.amount) from transactions t
            JOIN products p
            ON t.product_id = p.id
            JOIN users u
            ON t.user_id = u.id
            WHERE u.id = :user_id
            AND p.type = 'DEBIT'
            AND t.type = 'WITHDRAWAL')>100000
            THEN 1 ELSE 0 END
            AS BOOLEAN) AS exist;
            """, nativeQuery = true)
    boolean ifTotalWithdrawalFromDebitAccountMoreThanHundredThousand(UUID user_id);
}
