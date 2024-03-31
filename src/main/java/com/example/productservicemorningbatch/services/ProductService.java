package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dto.FakeStoreProductDto;
import com.example.productservicemorningbatch.dto.ProductDto;
import com.example.productservicemorningbatch.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Map<String,Object> fields);
    Product replaceProduct(Long id, ProductDto productDto);
    Product createProduct(FakeStoreProductDto fakeStoreProductDto);
    FakeStoreProductDto deleteProduct(Long id);
}
