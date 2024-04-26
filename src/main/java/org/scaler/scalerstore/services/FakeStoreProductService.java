package org.scaler.scalerstore.services;

import org.scaler.scalerstore.dtos.FakeStoreProductDTO;
import org.scaler.scalerstore.models.Category;
import org.scaler.scalerstore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setId(fakeStoreProductDTO.getId());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        return product;
    }

    private FakeStoreProductDTO convertProductToFakeStoreProductDTO(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        return fakeStoreProductDTO;
    }


    @Override
    public Product getSingleProduct(long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
                );

        assert fakeStoreProductDTO != null;
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDTO[].class
        );

        ArrayList<Product> res = new ArrayList<>();
        assert fakeStoreProductDTOList != null;
        for (FakeStoreProductDTO dto: fakeStoreProductDTOList){
            res.add(convertFakeStoreProductDTOToProduct(dto));
        }
        return res;
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = convertProductToFakeStoreProductDTO(product);
//        FakeStoreProductDTO res = restTemplate.postForEntity("",
//                new HttpEntity<>(fakeStoreProductDTO),
//                FakeStoreProductDTO.class).getBody();
//        assert res != null;
//        return convertFakeStoreProductDTOToProduct(res);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO res = restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.POST, requestCallback, responseExtractor);
        assert res != null;
        return convertFakeStoreProductDTOToProduct(res);

    }

    @Override
    public Product replaceProduct(Product product, long id) {
        FakeStoreProductDTO fakeStoreProductDTO = convertProductToFakeStoreProductDTO(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO res = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        assert res != null;
        return convertFakeStoreProductDTOToProduct(res);
    }

    @Override
    public Product updateProduct(Product product, long id) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException ("Product with ID " + id + " not found");
        }
    }
}
