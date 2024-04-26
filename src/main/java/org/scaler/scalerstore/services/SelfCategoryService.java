package org.scaler.scalerstore.services;

import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.CategoryRepository;
import org.scaler.scalerstore.repositories.projections.CategoryWithName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    SelfCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategory(String name) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(name);

        if(optionalCategory.isEmpty()){
            return new Category(name);
        }

        return optionalCategory.get();
    }

    @Override
    public List<CategoryWithName> getAllCategories() {
        Optional<List<CategoryWithName>> categoryListOptional = categoryRepository.getAllCategory();
        if(categoryListOptional.isEmpty())
            throw new RuntimeException("No Categories Were Found");

        return categoryListOptional.get();
    }

    @Override
    public List<Product> getAllProductsCategory(String categoryName) {
        Optional<List<Product>> productListByCategory = categoryRepository.getProductsByCategoryName(categoryName);

        if(productListByCategory.isEmpty())
            throw new RuntimeException();
        return productListByCategory.get();
    }
}
