package org.scaler.scalerstore.controllers;


import org.scaler.scalerstore.dtos.FakeStoreProductDTO;
import org.scaler.scalerstore.dtos.ProductReqDTO;
import org.scaler.scalerstore.dtos.ProductResDTO;
import org.scaler.scalerstore.dtos.ResStatus;
import org.scaler.scalerstore.exceptions.ProductNotFoundException;
import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.scaler.scalerstore.services.CategoryService;
import org.scaler.scalerstore.services.FakeStoreProductService;
import org.scaler.scalerstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private final ProductResDTO productResDTO = new ProductResDTO();

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
//        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ProductResDTO getSingleProduct(@PathVariable(name = "id") long id) throws ProductNotFoundException {
            Product product = productService.getSingleProduct(id);
            productResDTO.setProduct(product);
            productResDTO.setStatus(ResStatus.SUCCESS);

        return productResDTO;
    }

    @PostMapping()
    public ProductResDTO addProduct(@RequestBody ProductReqDTO productReqDTO){
        try {

            Product.ProductBuilder pb = Product.builder();
            pb.price(productReqDTO.getPrice());
            pb.title(productReqDTO.getTitle());
            pb.imageUrl(productReqDTO.getImage());
            pb.description(productReqDTO.getDescription());
            pb.category(productReqDTO.getCategory());
            Product product = pb.build();

            productResDTO.setProduct(productService.addProduct(product));
            productResDTO.setStatus(ResStatus.SUCCESS);
        } catch (Exception e){
            productResDTO.setMessage(e.getMessage());
            productResDTO.setStatus(ResStatus.FAILURE);
        }

        return productResDTO;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@RequestBody Product product,@PathVariable(name = "id")long id) throws ProductNotFoundException {
        return productService.replaceProduct(product,id);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable(name="id")long id) throws ProductNotFoundException {
        return productService.updateProduct(product,id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deletion Failed");
        }
    }


}
