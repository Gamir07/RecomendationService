package ru.ggainullin.recomendationservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRecommendations {
    private List<Recommendation> recommendationList;
}
