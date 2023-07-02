package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.data.entities.Category;
import com.example.demo.dto.request.category.CreateCategoryRequest;
import com.example.demo.dto.response.category.CategoryResponse;

@Component
public class CategoryMapper {
    public Category toCategory(CreateCategoryRequest createCategoryRequest) {
        return Category.builder()
                .name(createCategoryRequest.getCategoryName())
                .prefixEquipmentCode(createCategoryRequest.getPrefixEquipmentCode())
                .description(createCategoryRequest.getDescription())
                .isDeleted(false)
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .prefixEquipmentCode(category.getPrefixEquipmentCode())
                .description(category.getDescription())
                .isDeleted(category.isDeleted())
                .build();
    }

    public List<CategoryResponse> toListCategoriesResponse(List<Category> categoryList) {
        return categoryList.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }
}
