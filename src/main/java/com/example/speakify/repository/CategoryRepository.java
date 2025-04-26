package com.example.speakify.repository;

import com.example.speakify.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByMainCategory(String mainCategory);
    boolean existsBySubCategory(String subCategory);
    Optional<Category> findByMainCategoryAndSubCategoryIsNull(String mainCategory);
    boolean existsByMainCategoryAndSubCategoryIsNull(String mainCategory);
    List<Category> findAllByMainCategory(String mainCategory);
    Optional<Category> findByMainCategoryAndSubCategory(String mainCategory, String subCategory);
    boolean existsByMainCategoryAndSubCategory(String mainCategory, String subCategory);
}
