package com.nj_projects.productspring.services;

import com.nj_projects.productspring.models.Category;
import com.nj_projects.productspring.models.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getInCategory(String category);

}
