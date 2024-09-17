package ru.ggainullin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ggainullin.recomendationservice.UserRecommendations;
import ru.ggainullin.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RecommendationController {

    private final TransactionRepository service;

    @GetMapping("recommendation/{user_id}")
    public UserRecommendations recommendation(@PathVariable UUID user_id) {

        return new UserRecommendations(List.of());
    }
}
