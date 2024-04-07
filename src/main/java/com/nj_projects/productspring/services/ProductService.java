package com.nj_projects.productspring.services;

import com.nj_projects.productspring.models.Category;
import com.nj_projects.productspring.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    String deleteProduct(Long id);

}
