package ru.ggainullin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ggainullin.dto.RuleDTO;
import ru.ggainullin.dto.RuleDTOMapping;
import ru.ggainullin.entities.QueryFabric;
import ru.ggainullin.entities.QueryType;
import ru.ggainullin.entities.Rule;
import ru.ggainullin.queries.Query;
import ru.ggainullin.repository.RuleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository repository;


    public RuleDTO createRule(RuleDTO rule) {
        List<Query> queryList = rule.getQueryList();
        if (!validate(queryList)) {
            throw new RuntimeException();
        }
        Rule newRule = RuleDTOMapping.mapDTOToRule(rule);
        repository.save(newRule);
        return rule;
    }

    public void deleteRule(UUID ruleId) {
        repository.deleteById(ruleId);
    }

    public List<RuleDTO> getAllRules() {
        return repository.findAll().stream()
                .map(RuleDTOMapping::mapRuleToDTO)
                .collect(Collectors.toList());
    }

    private boolean validate(List<Query> queryList) {
        for (Query value : queryList) {
            QueryType query = value.getQuery();
            List<String> arguments = value.argumentsList();
            boolean negate = value.isNegate();
            try {
                QueryFabric.query(query, arguments, negate);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Failed to validate query: " + query, e);
            }
        }
        return true;
    }

}

//public record DynamicRecommendationQuery(
//        @JsonProperty("query") QueryType queryType,
//        @JsonProperty("arguments") List arguments,
//        @JsonProperty("negate") boolean shouldNegateQuery
//) {



