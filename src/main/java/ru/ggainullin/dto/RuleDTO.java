package ru.ggainullin.dto;

import lombok.Data;
import ru.ggainullin.queries.Query;

import java.util.List;
import java.util.UUID;

@Data
public class RuleDTO {

    private String product_name;
    private UUID product_id;
    private String product_text;
    private List<Query> queryList;
}
