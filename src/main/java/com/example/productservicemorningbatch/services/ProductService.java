package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.example.productservicemorningbatch.dtos.ProductDto;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import com.example.productservicemorningbatch.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(Long id) throws invalidProductIdException;
    List<Product> getAllProducts();
    //Map<String,Object> updateProduct(Long id, Map<String,Object> fields);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, ProductDto productDto);
    //Product createProduct(FakeStoreProductDto fakeStoreProductDto);
    Product createProduct(Product product);
    FakeStoreProductDto deleteProduct(Long id);
}
