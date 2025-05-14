package com.example.speakify.repository;

import com.example.speakify.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByMainCategory(String mainCategory);
    List<Category> findAllByMainCategory(String mainCategory);
    Optional<Category> findById(String id);
    Optional<Category> findByMainCategory(String mainCategory);
}
