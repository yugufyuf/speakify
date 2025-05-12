package com.example.speakify.controller;

import com.example.speakify.dto.request.CategoryRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.BookByCategoryResponse;
import com.example.speakify.dto.response.CategoryResponse;
import com.example.speakify.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    //public
    @GetMapping()
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/categories")
    ApiResponse<List<BookByCategoryResponse>> getCategories() {
        return ApiResponse.<List<BookByCategoryResponse>>builder()
                .result(categoryService.getAllCategoryWithBooks())
                .build();
    }

    //public
    @GetMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable("categoryId") String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategoryById(categoryId))
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable("categoryId") String categoryId,@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(categoryId, request))
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/main")
    ApiResponse<String> deleteMainCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.<String>builder()
                .result(categoryService.deleteMainCategory(request))
                .build();
    }

}
