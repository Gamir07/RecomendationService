package ru.ggainullin.queries;

import ru.ggainullin.entities.CompareOperation;
import ru.ggainullin.entities.ProductType;
import ru.ggainullin.entities.TransactionType;
import ru.ggainullin.repository.RuleRepository;

import java.util.List;
import java.util.UUID;

public class TransactionSumCompareQuery extends Query {
    private final ProductType productType;
    private final TransactionType transactionType;
    private final CompareOperation compareOperation;
    private final int amount;


    public TransactionSumCompareQuery(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new IllegalArgumentException("Принимаю только 4 аргумента");
        }
        this.productType = ProductType.valueOf(arguments.get(0));
        this.transactionType = TransactionType.valueOf(arguments.get(1));
        compareOperation = new CompareOperation(arguments.get(2));
        amount = Integer.parseInt(arguments.get(3));

    }

    @Override
    public boolean query(UUID userId, RuleRepository repository) {
        int sum = repository.transactionSumCompare(userId, productType, transactionType);
        return compareOperation.compare(sum, amount);

    }
}
