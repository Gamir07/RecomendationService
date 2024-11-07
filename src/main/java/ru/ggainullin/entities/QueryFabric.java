package ru.ggainullin.entities;

import ru.ggainullin.queries.*;

import java.util.List;

public class QueryFabric {
    public static Query query(QueryType rule, List<String> arguments, boolean negate) {
        return switch (rule) {
            case USER_OF -> new UserOfQuery(arguments);
            case ACTIVE_USER_OF -> new ActiveUserOfQuery(arguments);
            case TRANSACTION_SUM_COMPARE -> new TransactionSumCompareQuery(arguments);
            case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW -> new TransactionSumCompareDepositWithdrawQuery(arguments);
        };
    }
}
