package ru.ggainullin.recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Data
@Component
public class UserRecommendations {

    private List<Recommendation> recommendationList;

}
