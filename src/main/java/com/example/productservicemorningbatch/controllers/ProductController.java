package com.example.productservicemorningbatch.controllers;
import com.example.productservicemorningbatch.dto.FakeStoreProductDto;
import com.example.productservicemorningbatch.dto.ProductDto;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService=productService;
    }

    //localhost:8080/products/30
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product= productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
     }
    //localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
        //return new ArrayList<>();
    }
    @PostMapping("/")
    public Product createProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) {
        System.out.println("abc");
        return productService.createProduct(fakeStoreProductDto);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Map<String,Object> fields){
        return productService.updateProduct(id,fields);
        //return new Product();
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto){
        return productService.replaceProduct(id,productDto);
    }
    @DeleteMapping("/{id}")
    public FakeStoreProductDto deleteProduct(@PathVariable("id") Long id){
        System.out.println(id);
        return productService.deleteProduct(id);
    }
}
