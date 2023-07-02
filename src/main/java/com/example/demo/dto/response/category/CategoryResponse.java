package com.example.demo.dto.response.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
    private long id;
    private String name;
    private String prefixEquipmentCode;
    private String description;
    private boolean isDeleted;
}
