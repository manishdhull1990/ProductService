package com.example.productservicemorningbatch.repositories;

import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long Id);

    Optional<Product> findByTitleAndDescription(String title,String description);

    Optional<Product> findByTitleContaining(String word);

    Optional<Product> findTopThreeByTitle(String title); //limit the result by 3

    Optional<Product>  findByCategory(Category category);
}
