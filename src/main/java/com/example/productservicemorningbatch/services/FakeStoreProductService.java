package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dto.FakeStoreProductDto;
import com.example.productservicemorningbatch.dto.ProductDto;
import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
            return null ;
        }
        //Convert fakeStoreProductDto to product object.
        return convertFakeStoreToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {
//        ResponseEntity<FakeStoreProductDto[]> responseEntity= restTemplate.getForEntity(
//                "https://fakestoreapi.com/products",
//                FakeStoreProductDto[].class);
        FakeStoreProductDto[] FakeStoreProductDto= restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> productlist = new ArrayList<>();
    //    for(FakeStoreProductDto product:List.of(responseEntity.getBody())){
        for(FakeStoreProductDto product:FakeStoreProductDto){
            productlist.add(convertFakeStoreToProduct(product));
         }
        return productlist;
    }
    @Override
    public Product updateProduct() {
        return null;
    }
    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto= restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        System.out.println(fakeStoreProductDto);
        Product product1=convertFakeStoreToProduct(fakeStoreProductDto);
        System.out.println(product1);
        return product1;
    }
    @Override
    public Product createProduct() {
        return null;
    }
    @Override
    public void deleteProduct() {

    }
}
