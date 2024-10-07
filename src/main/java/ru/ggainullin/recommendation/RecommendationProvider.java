package ru.ggainullin.recommendation;

import java.util.Optional;
import java.util.UUID;

public interface RecommendationProvider {
    Optional<Recommendation> provideRecommendationForUser(UUID userId);
}

