package org.scaler.scalerstore.repositories;

import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.projections.CategoryWithName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category save(Category category);

    public Optional<Category> findCategoryByName(String name);

    @Query("select c.name as name from Category c")
    public Optional<List<CategoryWithName>> getAllCategory();

    @Query("select p from Category c LEFT JOIN Product p on c.id=p.category.id where c.name=:categoryName")
    public Optional<List<Product>> getProductsByCategoryName(@Param("categoryName")String categoryName);
}
