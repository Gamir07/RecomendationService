package ru.ggainullin.service;

import java.util.Map;
import java.util.UUID;

public interface TransactionService {
    Map<String, Integer> recommendation(UUID user_id);
}
