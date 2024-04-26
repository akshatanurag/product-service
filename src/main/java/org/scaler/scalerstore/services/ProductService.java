package org.scaler.scalerstore.services;

import org.scaler.scalerstore.dtos.FakeStoreProductDTO;
import org.scaler.scalerstore.exceptions.ProductNotFoundException;
import org.scaler.scalerstore.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id) throws ProductNotFoundException;

    List<Product> getAllProducts() throws ProductNotFoundException;

    Product addProduct(Product product);

    Product replaceProduct(Product product,long id) throws ProductNotFoundException;

    Product updateProduct(Product product,long id) throws ProductNotFoundException;

    void deleteProduct(long id);
}
