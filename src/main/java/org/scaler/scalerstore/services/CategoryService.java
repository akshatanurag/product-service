package org.scaler.scalerstore.services;

import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.projections.CategoryWithName;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category addCategory(Category category);

    public Category findCategory(String name);

    public List<CategoryWithName> getAllCategories();

    List<Product> getAllProductsCategory(String categoryName);
}
