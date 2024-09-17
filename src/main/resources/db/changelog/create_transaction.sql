-- liquibase formatted sql

-- changeset ggainullin:1

CREATE TABLE transactions
(
  id UUID PRIMARY KEY,
  type VARCHAR(56),
  user_id UUID,
  product_id UUID,
  amount INTEGER,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
)