package ru.ggainullin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ggainullin.recommendation.UserRecommendations;
import ru.ggainullin.service.RecommendationService;
import ru.ggainullin.service.RuleService;

import java.util.UUID;

@RestController
@RequestMapping("api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final RuleService ruleService;


    @GetMapping("/{user_id}")
    public UserRecommendations recommendation(@PathVariable("user_id") UUID userId) {
        return recommendationService.recommendations(userId);
    }
}
