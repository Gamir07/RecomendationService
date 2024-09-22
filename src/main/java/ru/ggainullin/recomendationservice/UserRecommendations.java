package ru.ggainullin.recomendationservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class UserRecommendations {

    @Autowired
    private List<RecommendationProvider> recommendationList;


}
