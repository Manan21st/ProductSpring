package com.nj_projects.productspring.services;

import com.nj_projects.productspring.exceptions.CategoryNotFoundException;
import com.nj_projects.productspring.exceptions.ProductNotFoundException;
import com.nj_projects.productspring.models.Category;
import com.nj_projects.productspring.models.Product;
import com.nj_projects.productspring.repositories.CategoryRepository;
import com.nj_projects.productspring.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
//@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id,"Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        if(category.getId() == null){
            product.setCategory(categoryRepository.save(category));
        }
        Product tempProduct = productRepository.save(product);
        Optional<Category> tempCategory = categoryRepository.findById(category.getId());
        if(tempCategory.isEmpty()){
            throw new CategoryNotFoundException("Invalid category id passed");
        }
        tempProduct.setCategory(tempCategory.get());
        return tempProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }
}
