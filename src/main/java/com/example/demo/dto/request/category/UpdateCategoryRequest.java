package com.example.demo.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateCategoryRequest {
    @NotBlank(message = "Category name is required")
    @Pattern(regexp = "^.*[A-Za-z].*[\\s]?+.*$", message = "Invalid category name")
    @Size(max = 100, message = "Category name maximum 100 characters")
    private String categoryName;

    @NotBlank(message = "Description is required")
    @Pattern(regexp = "^.*[A-Za-z].*[\\s]?+.*$", message = "Invalid description")
    @Size(max = 100, message = "Description maximum 100 characters")
    private String description;
}
