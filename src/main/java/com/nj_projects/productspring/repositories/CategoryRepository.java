package com.nj_projects.productspring.repositories;

import com.nj_projects.productspring.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Override
    Optional<Category> findById(Long id);

    @Override
    Category save(Category category);
}
