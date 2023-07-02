package com.example.demo.services.category.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.entities.Category;
import com.example.demo.data.repositories.CategoryRepository;
import com.example.demo.dto.request.category.CreateCategoryRequest;
import com.example.demo.dto.request.category.UpdateCategoryRequest;
import com.example.demo.dto.response.category.CategoryResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.services.category.CategoryService;

import lombok.Builder;

@Service
@Builder
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        if (!categoryRepository.findByName(createCategoryRequest.getCategoryName()).isEmpty()) {
            throw new BadRequestException("Category is already existed. Please enter a different category");
        }
        if (!categoryRepository.findByPrefixEquipmentCode(createCategoryRequest.getPrefixEquipmentCode()).isEmpty()) {
            throw new BadRequestException("Prefix is already existed. Please enter a different prefix");
        }
        Category category = categoryMapper.toCategory(createCategoryRequest);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAllByIsDeletedFalse();
        return categoryMapper.toListCategoriesResponse(categoryList);
    }

    @Override
    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        if (categoryOpt.get().isDeleted()) {
            throw new BadRequestException("Category is deleted");
        }
        Category category = categoryOpt.get();
        category.setName(updateCategoryRequest.getCategoryName());
        category.setDescription(updateCategoryRequest.getDescription());
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        if (category.get().isDeleted()) {
            throw new BadRequestException("Category is deleted");
        }
        return categoryMapper.toCategoryResponse(category.get());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        if (categoryOpt.get().isDeleted()) {
            throw new BadRequestException("Category is deleted");
        }
        categoryOpt.get().setDeleted(true);
        categoryRepository.save(categoryOpt.get());
    }
}