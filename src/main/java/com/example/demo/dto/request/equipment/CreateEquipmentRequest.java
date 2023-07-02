package com.example.demo.dto.request.equipment;

import lombok.*;

@Getter
@Setter
@Builder
public class CreateEquipmentRequest {
    private String equipmentName;
    private String description;
    private int categoryId;
}
