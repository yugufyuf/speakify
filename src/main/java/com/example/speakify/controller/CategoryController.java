package com.example.speakify.controller;

import com.example.speakify.dto.request.CategoryRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.CategoryResponse;
import com.example.speakify.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable("categoryId") String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategoryById(categoryId))
                .build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable("categoryId") String categoryId,@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(categoryId, request))
                .build();
    }

    @DeleteMapping("/main")
    ApiResponse<String> deleteMainCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.<String>builder()
                .result(categoryService.deleteMainCategory(request))
                .build();
    }

    @DeleteMapping("/sub")
    ApiResponse<String> deleteSubCategory(@RequestBody @Valid CategoryRequest request) {
        return ApiResponse.<String>builder()
                .result(categoryService.deleteSubCategory(request))
                .build();
    }
}
