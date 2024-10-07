package ru.ggainullin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ggainullin.recommendation.Recommendation;
import ru.ggainullin.recommendation.RecommendationProvider;
import ru.ggainullin.recommendation.UserRecommendations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final List<RecommendationProvider> recommendationsProvider;
    private final UserRecommendations userRecommendations;

        public UserRecommendations recommendations(UUID userId) {
        List<Recommendation> recommendations = new ArrayList<>();
        for (RecommendationProvider recommendationProvider : recommendationsProvider) {
            recommendationProvider.provideRecommendationForUser(userId).ifPresent(recommendations::add);
        }
        userRecommendations.setRecommendationList(recommendations);
        return userRecommendations;
    }
}
