package org.scaler.scalerstore.repositories;

import org.scaler.scalerstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByTitleContaining(String word);

    Optional<Product> findById(long id);

    Product save(Product product);

    void deleteById(Long id);

    @Query("select p from Product p")
    Optional<List<Product>> getAllProducts();
}
