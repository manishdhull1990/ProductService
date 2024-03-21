package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dto.FakeStoreProductDto;
import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private Product convertFakeStoreToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product=new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category=new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
        //Call the FakeStore Api to get the product with the given ID here
        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                        FakeStoreProductDto.class);

        if (fakeStoreProductDto==null){
            return null;
        }
        //Convert fakeStoreProductDto to product object.
        return convertFakeStoreToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {
        List<Product> productlist = new ArrayList<>();;
        ResponseEntity<FakeStoreProductDto[]> responseEntity= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        for(FakeStoreProductDto product:List.of(responseEntity.getBody())){
            productlist.add(convertFakeStoreToProduct(product));
         }
        return productlist;
    }
    @Override
    public Product updateProduct() {
        return null;
    }
    @Override
    public Product replaceProduct() {
        return null;
    }
    @Override
    public Product createProduct() {
        return null;
    }
    @Override
    public void deleteProduct() {

    }
}
