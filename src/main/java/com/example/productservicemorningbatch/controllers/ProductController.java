package com.example.productservicemorningbatch.controllers;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService=productService;
    }

    //localhost:8080/products/30
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
     }
    //localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
        //return new ArrayList<>();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return new Product();
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return new Product();
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id){

    }
}
