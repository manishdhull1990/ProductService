package com.example.productservicemorningbatch.controllers;
import com.example.productservicemorningbatch.commons.AuthenticationCommons;
import com.example.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.example.productservicemorningbatch.dtos.ProductDto;
import com.example.productservicemorningbatch.dtos.Role;
import com.example.productservicemorningbatch.dtos.UserDto;
import com.example.productservicemorningbatch.exceptions.ProductControllerSpecificException;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import com.example.productservicemorningbatch.models.Product;
import com.example.productservicemorningbatch.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthenticationCommons authenticationCommons;
    ProductController(@Qualifier("selfProductService") ProductService productService,
                      AuthenticationCommons authenticationCommons){
        this.productService=productService;
        this.authenticationCommons=authenticationCommons;
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
    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token){
        //Validate the token using UserService
        UserDto userDto = authenticationCommons.validateToken(token);

        if (userDto == null){
            //token is invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        /*boolean isAdmin=false;
        for (Role role : userDto.getRoles()){
            if (role.equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }

        if (!isAdmin){
            return null;
        }*/

        List<Product> products=productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
        //return new ArrayList<>();
        /*int a=1/0;
        return null;*/
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PatchMapping("/{id}")
    //public Map<String,Object> updateProduct(@PathVariable("id") Long id,@RequestBody Map<String,Object> fields){
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
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
