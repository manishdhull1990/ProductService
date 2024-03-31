package com.example.productservicemorningbatch.controllers;
import com.example.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.example.productservicemorningbatch.dtos.ProductDto;
import com.example.productservicemorningbatch.exceptions.ProductControllerSpecificException;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws invalidProductIdException {
        /*Product product=null;
        try{
            product= productService.getProductById(id);
        }
        catch(RuntimeException e){
            System.out.println("Something went wrong");
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);*/
        Product product= productService.getProductById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
        /*int a=1/0;
        return null;*/
     }
    //localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
        //return new ArrayList<>();
        /*int a=1/0;
        return null;*/
    }
    @PostMapping("/")
    public Product createProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) {
        System.out.println("abc");
        return productService.createProduct(fakeStoreProductDto);
    }
    @PatchMapping("/{id}")
    public Map<String,Object> updateProduct(@PathVariable("id") Long id,@RequestBody Map<String,Object> fields){
    //public UpdateProductDto updateProduct(@PathVariable("id") Long id,@RequestBody UpdateProductDto updateProductDto){
        return productService.updateProduct(id,fields);
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

    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<Void> handleProductControllerSpecificException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
