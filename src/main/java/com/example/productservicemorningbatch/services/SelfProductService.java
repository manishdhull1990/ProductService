package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.example.productservicemorningbatch.dtos.ProductDto;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.repositories.CategoryRepository;
import com.example.productservicemorningbatch.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws invalidProductIdException {
         Optional<Product> optionalProduct=productRepository.findById(id);
         if (optionalProduct.isEmpty()){
             return null;
         }
         return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Map<String, Object> updateProduct(Long id, Map<String, Object> fields) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category=product.getCategory();
        if (category.getId()==null){
            Category savedCategory=categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        return null;
    }
}
