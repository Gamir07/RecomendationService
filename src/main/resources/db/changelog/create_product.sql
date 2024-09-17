-- liquibase formatted sql

-- changeset ggainullin:1

CREATE TABLE products
(
  id UUID PRIMARY KEY,
  type VARCHAR(56),
  name VARCHAR(56)
)