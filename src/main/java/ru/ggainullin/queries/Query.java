package ru.ggainullin.queries;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.ggainullin.entities.QueryType;
import ru.ggainullin.repository.RuleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public abstract class Query {
    @Enumerated(EnumType.STRING)
    private QueryType query;
    private String arguments;
    private boolean negate;

    public abstract boolean query(UUID userId, RuleRepository repository);

    public List<String> argumentsList(){
        return Arrays.stream(arguments.split(",")).toList();
    }


}
