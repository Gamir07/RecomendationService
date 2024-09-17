-- liquibase formatted sql

-- changeset ggainullin:1

CREATE TABLE users
(
  id UUID PRIMARY KEY,
  registration_date TEXT,
  username VARCHAR(56),
  first_name VARCHAR(56),
  last_name VARCHAR(56)
)