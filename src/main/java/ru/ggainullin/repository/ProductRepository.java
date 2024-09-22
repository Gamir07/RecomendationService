package ru.ggainullin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggainullin.entities.Product;
import ru.ggainullin.entities.ProductType;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = """
            select id from products
            where type = :type;
            """, nativeQuery = true)
    List<UUID> listOfProducts(ProductType type);

}
