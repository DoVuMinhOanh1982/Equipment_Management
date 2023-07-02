package com.example.demo.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
public class CreateCategoryRequest {
    @NotBlank(message = "Category name is required")
    @Pattern(regexp = "^.*[A-Za-z].*[\\s]?+.*$", message = "Invalid category name")
    @Size(max = 100, message = "Category name maximum 100 characters")
    private String categoryName;

    @NotBlank(message = "Prefix EquipmentCode is required")
    @Pattern(regexp = "^.*[A-Za-z].*$", message = "Invalid Prefix EquipmentCode")
    @Size(max = 2, message = "Prefix EquipmentCode maximum 2 characters")
    private String prefixEquipmentCode;

    @NotBlank(message = "Description is required")
    @Pattern(regexp = "^.*[A-Za-z].*[\\s]?+.*$", message = "Invalid description")
    @Size(max = 100, message = "Description maximum 100 characters")
    private String description;
}
