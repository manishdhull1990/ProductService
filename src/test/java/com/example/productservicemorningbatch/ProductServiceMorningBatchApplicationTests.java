package com.example.productservicemorningbatch;

import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.repositories.CategoryRepository;
import com.example.productservicemorningbatch.repositories.ProductRepository;
import com.example.productservicemorningbatch.repositories.projections.productWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceMorningBatchApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries(){
        List<productWithIdAndTitle> products=productRepository.someRandomQuery();
        for(productWithIdAndTitle product:products){
            System.out.print(product.getId()+" ");
            System.out.print(product.getTitle());
        }

        Product product1=productRepository.doSomethingSQL();

        System.out.println("DEBUG");

        categoryRepository.deleteById(52L);
    }

}
