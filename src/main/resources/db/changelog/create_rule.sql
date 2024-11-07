-- liquibase formatted sql

-- changeset ggainullin:1

CREATE TABLE rules
(
  rule_id UUID PRIMARY KEY,
  product_name VARCHAR(56)
  product_id UUID,
  product_text VARCHAR(256),


)