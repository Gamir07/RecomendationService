package ru.ggainullin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ggainullin.queries.Query;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rule")
public class Rule {

    private UUID rule_id;
    private String product_name;
    private UUID product_id;
    private String product_text;
    private List<Query> queryList;


}
