package ru.ggainullin.recomendationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Recommendation {

    private String name;
    private UUID id;
    private String recommendation;

}
