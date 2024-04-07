package com.nj_projects.productspring.controllers;

import com.nj_projects.productspring.models.Category;
import com.nj_projects.productspring.models.Product;
import com.nj_projects.productspring.services.CategoryService;
import com.nj_projects.productspring.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getInCategory(@PathVariable("category") String category){
        return categoryService.getInCategory(category);
    }
}
