package ru.ggainullin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ggainullin.recomendationservice.Recommendation;
import ru.ggainullin.recomendationservice.RecommendationProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final List<RecommendationProvider> recommendations;
    private List<Recommendation> userRecommendations;

    public List<Recommendation> recommendations(UUID userId) {
        userRecommendations = new ArrayList<>();
        for (RecommendationProvider recommendationProvider : recommendations) {
            Optional<Recommendation> recommendation1 = recommendationProvider.provideRecommendationForUser(userId);
            recommendation1.ifPresent(recommendation -> userRecommendations.add(recommendation));
        }
        return userRecommendations;
    }
}
