package ru.ggainullin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ggainullin.dto.RuleDTO;
import ru.ggainullin.service.RuleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/recommendation")
public class RulesController {

    private final RuleService service;

    @PostMapping
    public RuleDTO createRule(@RequestBody RuleDTO rule) {
        return service.createRule(rule);
    }

    @DeleteMapping("/{ruleId}")
    public void deleteRule(@PathVariable UUID ruleId) {
        service.deleteRule(ruleId);
    }

    @GetMapping
    public List<RuleDTO> getAllRules() {
        return service.getAllRules();
    }
}
