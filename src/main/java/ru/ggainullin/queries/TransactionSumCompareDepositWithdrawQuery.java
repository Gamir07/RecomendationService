package ru.ggainullin.queries;

import ru.ggainullin.entities.CompareOperation;
import ru.ggainullin.entities.ProductType;
import ru.ggainullin.entities.TransactionType;
import ru.ggainullin.repository.RuleRepository;

import java.util.List;
import java.util.UUID;

public class TransactionSumCompareDepositWithdrawQuery extends Query {

    private final ProductType productType;
    private final CompareOperation compareOperation;

    public TransactionSumCompareDepositWithdrawQuery(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Принимаю только 2 аргумента");
        }
        this.productType = ProductType.valueOf(arguments.get(0));
        compareOperation = new CompareOperation(arguments.get(1));
    }

    @Override
    public boolean query(UUID userId, RuleRepository repository) {
        int deposit = repository.transactionSumCompare(userId, productType, TransactionType.DEPOSIT);
        int withdrawal = repository.transactionSumCompare(userId, productType, TransactionType.WITHDRAWAL);
        return compareOperation.compare(deposit, withdrawal);

    }
}
