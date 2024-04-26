package org.scaler.scalerstore.controllers;

import org.junit.jupiter.api.Test;
import org.scaler.scalerstore.dtos.ProductResDTO;
import org.scaler.scalerstore.exceptions.ProductNotFoundException;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.repositories.ProductRepository;
import org.scaler.scalerstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @MockBean
    @Qualifier("selfProductService") ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductController productController;


    @Test
    void testGetAllProducts() throws ProductNotFoundException {
        List<Product> productArrayList = new ArrayList<>();

        Product p1 = new Product();
        p1.setTitle("Iphone 15");
        productArrayList.add(p1);

        Product p2 = new Product();
        p1.setTitle("Iphone 15 Pro");
        productArrayList.add(p2);

        Product p3 = new Product();
        p1.setTitle("Iphone 15 Pro Max");
        productArrayList.add(p3);

        when(
                productService.getAllProducts()
        ).thenReturn(
                productArrayList
        );


        ResponseEntity<List<Product>> productsListRes = productController.getAllProducts();

        assertEquals(productArrayList,productsListRes.getBody());

    }

    @Test
    void testGetSingleProduct() throws ProductNotFoundException {
        long id = 1;
        Product p = new Product();
        p.setTitle("Iphone 14");

        when(
                productService.getSingleProduct(id)
        ).thenReturn(
                p
        );

        ProductResDTO pResDTO = productController.getSingleProduct(id);
        Product pRes = pResDTO.getProduct();

        assertEquals(p,pRes);
    }

    @Test
    void testGetSingleProductThrowProductNotFoundException() throws ProductNotFoundException {
        when(productService.getSingleProduct(anyLong())).thenThrow(new ProductNotFoundException("Product Not Found"));

        assertThrows(ProductNotFoundException.class,()->productController.getSingleProduct(1L));
    }
}