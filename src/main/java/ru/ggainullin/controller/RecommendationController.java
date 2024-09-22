package ru.ggainullin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ggainullin.recomendationservice.Recommendation;
import ru.ggainullin.service.RecommendationService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping("recommendation/{user_id}")
    public Recommendation recommendation(@PathVariable UUID user_id) {
        return service.recommendations(user_id);
    }
}
