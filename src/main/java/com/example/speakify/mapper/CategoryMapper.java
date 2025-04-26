package com.example.speakify.mapper;

import com.example.speakify.dto.request.CategoryRequest;
import com.example.speakify.dto.response.CategoryResponse;
import com.example.speakify.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryRequest request);
    List<CategoryResponse> toCategoryResponseList(List<Category> categoryList);
}
