package ru.ggainullin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ggainullin.entities.Rule;
import ru.ggainullin.service.RuleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/recommendation")
public class RulesController {

    private final RuleService service;

    @PostMapping
    public Rule createRule(@RequestBody Rule rule) {
        return service.createRule(rule);
    }

    @DeleteMapping("/{ruleId}")
    public void deleteRule(@PathVariable UUID ruleId) {
        service.deleteRule(ruleId);
    }

    @GetMapping
    public List<Rule> getAllRules() {
        return service.getAllRules();
    }
}
