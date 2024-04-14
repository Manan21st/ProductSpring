package com.nj_projects.productspring.controllers;

import com.nj_projects.productspring.models.Product;
import com.nj_projects.productspring.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
