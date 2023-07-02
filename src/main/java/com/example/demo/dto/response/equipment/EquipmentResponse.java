package com.example.demo.dto.response.equipment;

import com.example.demo.data.constants.EStatus;

import lombok.*;

@Builder
@Getter
@Setter
public class EquipmentResponse {
    private long id;
    private String equipmentName;
    private String equipmentCode;
    private String description;
    private String categoryName;
    private EStatus status;
    private boolean isDeleted;
}
