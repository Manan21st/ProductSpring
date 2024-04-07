package com.nj_projects.productspring.services;

import com.nj_projects.productspring.dtos.FakeStoreProductDto;
import com.nj_projects.productspring.exceptions.ProductNotFoundException;
import com.nj_projects.productspring.models.Category;
import com.nj_projects.productspring.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService,CategoryService{

    private RestTemplate restTemplate=new RestTemplate();

    @Override
    public List<Product> getAllProducts() {
        ParameterizedTypeReference<List<FakeStoreProductDto>> responseType= new ParameterizedTypeReference<>() {};

        List<FakeStoreProductDto> fakeStoreProductDtos = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                org.springframework.http.HttpMethod.GET,
                null,
                responseType
        ).getBody();

        List<Product> products= new ArrayList<>();

        if(fakeStoreProductDtos == null){
            return new ArrayList<>();
        }

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = new Product();
            product.setId(fakeStoreProductDto.getId());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDto.getCategory());
            product.setImageUrl(fakeStoreProductDto.getImage());
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long id) {

        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class
        );

        Product product= new Product();

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException(id,"Please pass a valid Product ID");
        }

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }

    @Override
    public Product createProduct(Product product) {

        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());

        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());

        restTemplate.put(
                "https://fakestoreapi.com/products/"+id,
                fakeStoreProductDto
        );

        return product;
    }

    @Override
    public String deleteProduct(Long id) {

        restTemplate.delete("https://fakestoreapi.com/products/"+id);

        return "Product deleted successfully!";
    }


    @Override
    public List<Category> getAllCategories() {
        ParameterizedTypeReference<List<String>> responseType= new ParameterizedTypeReference<>() {};

        List<String> strings = restTemplate.exchange(
                "https://fakestoreapi.com/products/categories",
                org.springframework.http.HttpMethod.GET,
                null,
                responseType
        ).getBody();

        List<Category> categories= new ArrayList<>();
        if(strings == null){
            return new ArrayList<>();
        }
        for(String string : strings){
            Category category= new Category();
            category.setName(string);
            category.setId((long)strings.indexOf(string));
            categories.add(category);
        }
        return categories;
    }

    @Override
    public List<Product> getInCategory(String category) {
        ParameterizedTypeReference<List<FakeStoreProductDto>> responsetype = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<FakeStoreProductDto>> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/category/"+category,
                HttpMethod.GET,
                null,
                responsetype
        );

        List<FakeStoreProductDto> fakeStoreProductDtos = responseEntity.getBody();

        List<Product> products= new ArrayList<>();

        if(fakeStoreProductDtos == null){
            return new ArrayList<>();
        }

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = new Product();
            product.setId(fakeStoreProductDto.getId());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDto.getCategory());
            product.setImageUrl(fakeStoreProductDto.getImage());
            products.add(product);
        }


        return products;
    }
}
