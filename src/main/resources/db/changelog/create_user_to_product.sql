-- liquibase formatted sql

-- changeset ggainullin:1

CREATE TABLE user_to_product
(
  user_id UUID REFERENCES users(id),
  product_id UUID REFERENCES products(id),
  PRIMARY KEY(user_id,product_id)
)