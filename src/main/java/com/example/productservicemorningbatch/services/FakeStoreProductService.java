package com.example.productservicemorningbatch.services;

import com.example.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.example.productservicemorningbatch.dtos.ProductDto;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import com.example.productservicemorningbatch.models.Category;
import com.example.productservicemorningbatch.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("fakeStoreProductService")
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
    public Product getProductById(Long id) throws invalidProductIdException {
        //Call the FakeStore Api to get the product with the given ID here
        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                        FakeStoreProductDto.class);
        if (fakeStoreProductDto==null){
            throw new invalidProductIdException(id,"Invalid product id passed");
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
    //public Map<String,Object> updateProduct(Long id, Map<String,Object> fields) {
    public Product updateProduct(Long id, Product product) {
        /*RequestCallback requestCallback = restTemplate.httpEntityCallback(fields, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto= restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        */

//        Map<String,Object> updateProduct=
//                restTemplate.patchForObject("https://fakestoreapi.com/products/"+id,
//                        fields, Map.class);
        //System.out.println(updateProduct);
        return product;
    }
    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto= restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        System.out.println(fakeStoreProductDto);
        Product putproduct=convertFakeStoreToProduct(fakeStoreProductDto);
        System.out.println(putproduct);
        return putproduct;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto newfakeStoreProductDto=
                restTemplate.postForObject("https://fakestoreapi.com/products/",product,
                        FakeStoreProductDto.class);
        Product createdproduct=convertFakeStoreToProduct(newfakeStoreProductDto);
        return createdproduct;
    }
    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        //RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        //HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, null, null);
        //Product deletedproduct=convertFakeStoreToProduct(fakeStoreProductDto);
        return fakeStoreProductDto;
    }
}
