package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dto.ProductDto;
import com.example.productservicemorningbatch.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct();
    Product replaceProduct(Long id, ProductDto productDto);
    Product createProduct();
    void deleteProduct();
}
