package ru.ggainullin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ggainullin.recomendationservice.Recommendation;
import ru.ggainullin.recomendationservice.RecommendationProvider;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    @Autowired
    private final List<RecommendationProvider> recommendations;

    public Recommendation recommendations(UUID userId) {
        Recommendation recommendation = null;
        for (RecommendationProvider rec : recommendations) {
            recommendation = rec.provideRecommendationForUser(userId).get();
        }
        return recommendation;
    }
}
