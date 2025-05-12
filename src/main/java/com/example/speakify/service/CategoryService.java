package com.example.speakify.service;

import com.example.speakify.dto.request.CategoryRequest;
import com.example.speakify.dto.response.BookByCategoryResponse;
import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.dto.response.CategoryResponse;
import com.example.speakify.entity.Book;
import com.example.speakify.entity.Category;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.CategoryMapper;
import com.example.speakify.repository.BookRepository;
import com.example.speakify.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    private final BookRepository bookRepository;

//    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
//        String main = categoryRequest.getMainCategory();
//        String sub = categoryRequest.getSubCategory();
//
//        boolean existsMain = categoryRepository.existsByMainCategory(main);
//        boolean existsSub = categoryRepository.existsBySubCategory(sub);
//
//        if (sub != null) {
//            if (main == null)
//                throw new AppException(ErrorCode.MAIN_CATEGORY_NOT_EXIST);
//            if (existsSub)
//                throw new AppException(ErrorCode.SUB_CATEGORY_EXIST);
//
//            // Nếu tồn tại bản ghi main=sub=null, ta cập nhật sub
//            Optional<Category> existing = categoryRepository.findByMainCategoryAndSubCategoryIsNull(main);
//            if (existing.isPresent()) {
//                Category category = existing.get();
//                category.setSubCategory(sub);
//                return categoryMapper.toCategoryResponse(categoryRepository.save(category));
//            }
//        } else {
//            if (existsMain && categoryRepository.existsByMainCategoryAndSubCategoryIsNull(main))
//                throw new AppException(ErrorCode.MAIN_CATEGORY_EXISTED);
//        }
//
//        return categoryMapper.toCategoryResponse(
//                categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
//    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(categoryRepository.existsByMainCategory(categoryRequest.getMainCategory()))
            throw new AppException(ErrorCode.MAIN_CATEGORY_EXISTED);
        return categoryMapper.toCategoryResponse(categoryRepository.save(categoryMapper.toCategory(categoryRequest)));
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    public List<BookByCategoryResponse> getAllCategoryWithBooks() {
        List<Category> categories = categoryRepository.findAll();
        List<BookByCategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            List<BookResponse> books = bookRepository.findAllByCategory_Id(category.getId());
            BookByCategoryResponse bookByCategoryResponse = BookByCategoryResponse.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getMainCategory())
                    .books(books)
                    .build();
            responses.add(bookByCategoryResponse);
        }
        return responses;
    }

    public CategoryResponse getCategoryById(String id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST)));
    }

    public CategoryResponse updateCategory(String id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        if(categoryRequest.getMainCategory() != null) {
            category.setMainCategory(categoryRequest.getMainCategory());
        }
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public String deleteMainCategory(CategoryRequest categoryRequest) {
        List<Category> categories = categoryRepository.findAllByMainCategory(categoryRequest.getMainCategory());
        categoryRepository.deleteAll(categories);
        if(categoryRepository.existsByMainCategory(categoryRequest.getMainCategory())) {
            return "Delete failed";
        }
        return "Delete successful";
    }
}