package ru.ggainullin.recomendationservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class Recommendation {

    private UUID id;
    private String name;
    private String recommendation;

}
