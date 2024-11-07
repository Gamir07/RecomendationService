package ru.ggainullin.queries;

import ru.ggainullin.entities.ProductType;
import ru.ggainullin.repository.RuleRepository;

import java.util.List;
import java.util.UUID;


public class UserOfQuery extends Query{

    private final ProductType type;

    public UserOfQuery(List<String> arguments) {
        if (arguments.size() != 1) {
        throw new IllegalArgumentException("Принимаю только один аргумент");
        }
        this.type = ProductType.valueOf(arguments.get(0));
    }

    @Override
    public boolean query(UUID userId, RuleRepository repository){
        return repository.userOf(userId,type);
    }

}
