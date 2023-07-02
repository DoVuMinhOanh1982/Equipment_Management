package com.example.demo.dto.request.equipment;

import lombok.*;

@Getter
@Setter
@Builder
public class UpdateEquipmentRequest {
    private String equipmentName;
    private String description;
}
