package com.example.demo.services.category;

import java.util.List;

import com.example.demo.dto.request.category.CreateCategoryRequest;
import com.example.demo.dto.request.category.UpdateCategoryRequest;
import com.example.demo.dto.response.category.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequest);

    CategoryResponse getCategoryById(Long categoryId);

    void deleteCategory(Long categoryId);

    List<CategoryResponse> getAllCategories();
}
