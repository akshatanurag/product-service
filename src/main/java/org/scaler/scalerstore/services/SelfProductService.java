package org.scaler.scalerstore.services;

import org.scaler.scalerstore.exceptions.ProductNotFoundException;
import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService{
    ProductRepository productRepository;

    CategoryService categoryService;

    @Autowired
    public SelfProductService(ProductRepository productRepository,CategoryService categoryService){

        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id" + id + "not found.");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        Optional<List<Product>> productList = productRepository.getAllProducts();

        if(productList.isEmpty()){
            throw new ProductNotFoundException("No Products were fround in the db");
        }

        return productList.get();
    }

    @Override
    public Product addProduct(Product product) {
        Category category = categoryService.findCategory(product.getCategory().getName());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Product product, long id) throws ProductNotFoundException {
        Category category = categoryService.findCategory(product.getCategory().getName());
        product.setCategory(category);

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Product Not Found");

        Product savedProduct = productOptional.get();

        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setImageUrl(product.getImageUrl());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setCategory(product.getCategory());

        return productRepository.save(savedProduct);
    }

    @Override
    public Product updateProduct(Product product, long id) throws ProductNotFoundException {
        Category category = categoryService.findCategory(product.getCategory().getName());
        product.setCategory(category);

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Not Found In DB");

        Product savedProduct = productOptional.get();

        if(product.getTitle()!=null)
            savedProduct.setTitle(product.getTitle());
        if(product.getPrice()!=null)
            savedProduct.setPrice(product.getPrice());
        if(product.getDescription()!=null)
            savedProduct.setDescription(product.getDescription());
        if(product.getImageUrl()!=null)
            savedProduct.setImageUrl(product.getImageUrl());
        if(product.getCategory()!=null)
            savedProduct.setCategory(product.getCategory());

        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
