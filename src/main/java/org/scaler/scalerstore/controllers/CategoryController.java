package org.scaler.scalerstore.controllers;

import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.projections.CategoryWithName;
import org.scaler.scalerstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryWithName> getAll(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}")
    public List<Product> getAllProductsByCategory(@PathVariable(name = "categoryName")String categoryName){
        return categoryService.getAllProductsCategory(categoryName);
    }

}
