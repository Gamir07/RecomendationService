package ru.ggainullin.entities;

import java.util.Set;

public class CompareOperation {

    private final String operation;

    public CompareOperation(String operation) {
        Set<String> compareOperation = Set.of(">", "<", "=", ">=", "<=");
        if (!compareOperation.contains(operation)) {
            throw new IllegalArgumentException();
        }
        this.operation = operation;
    }

    public boolean compare(int i, int i2) {
        return switch (operation) {
            case ">" -> i > i2;
            case "<" -> i < i2;
            case "=" -> i == i2;
            case ">=" -> i >= i2;
            case "<=" -> i <= i2;
            default -> throw new RuntimeException();
        };

    }
}
