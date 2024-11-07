package ru.ggainullin.dto;

import ru.ggainullin.entities.Rule;

public class RuleDTOMapping {

    public static RuleDTO mapRuleToDTO(Rule rule) {
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setProduct_id(rule.getProduct_id());
        ruleDTO.setProduct_text(rule.getProduct_text());
        ruleDTO.setProduct_name(rule.getProduct_name());
        return ruleDTO;
    }

    public static Rule mapDTOToRule(RuleDTO ruleDTO) {
        Rule rule = new Rule();
        rule.setProduct_id(ruleDTO.getProduct_id());
        rule.setProduct_text(ruleDTO.getProduct_text());
        rule.setProduct_name(ruleDTO.getProduct_name());
        return rule;
    }
}
