package ru.ggainullin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ggainullin.entities.Rule;
import ru.ggainullin.repository.RuleRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository repository;

    public Rule createRule(Rule rule) {
        String newRule = rule.getRule();
        if (validate(newRule)) {
            return repository.save(rule);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteRule(UUID ruleId) {
        repository.deleteById(ruleId);
    }

    public List<Rule> getAllRules() {
        return repository.findAll();
    }

    private boolean validate(String rule) {
        String[] queries = rule.split("%");

        Method[] declaredMethods = RuleRepository.class.getDeclaredMethods();
        return false;

    }

}
