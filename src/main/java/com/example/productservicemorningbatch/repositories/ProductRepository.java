package com.example.productservicemorningbatch.repositories;

import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.repositories.projections.productWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long Id);

    Optional<Product> findByTitleAndDescription(String title,String description);

    Optional<Product> findByTitleContaining(String word);

    Optional<Product> findTopThreeByTitle(String title); //limit the result by 3

    Optional<Product>  findByCategory(Category category);
    List<Product> findAll();

    void deleteById(Long Id);

    Product save(Product product);

    //    @Query("Custom Query") //HQL -> Hibernate Query Language
//    Optional<Product> someRandomQuery();
    // This method will return a Product with only ID and Title
    @Query("select p.id as id,p.title as title from Product p where p.price>10000 and p.title like '%pro%'")
    List<productWithIdAndTitle> someRandomQuery();

    @Query(value="select * from product p where p.id=1",nativeQuery = true)
    Product doSomethingSQL();
}
