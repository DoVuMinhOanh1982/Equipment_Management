package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.data.constants.EStatus;
import com.example.demo.data.entities.Equipment;
import com.example.demo.dto.request.equipment.CreateEquipmentRequest;
import com.example.demo.dto.response.equipment.EquipmentResponse;

@Component
public class EquipmentMapper {
    public Equipment toEquipment(CreateEquipmentRequest createEquipmentRequest) {
        return Equipment.builder()
                .name(createEquipmentRequest.getEquipmentName())
                .description(createEquipmentRequest.getDescription())
                .status(EStatus.AVAILABLE)
                .isDeleted(false)
                .build();
    }

    public EquipmentResponse toEquipmentResponse(Equipment equipment) {
        return EquipmentResponse.builder()
                .id(equipment.getId())
                .equipmentName(equipment.getName())
                .equipmentCode(equipment.getEquipmentCode())
                .description(equipment.getDescription())
                .categoryName(equipment.getCategory().getName())
                .status(equipment.getStatus())
                .isDeleted(equipment.isDeleted())
                .build();
    }

    public List<EquipmentResponse> toListEquipmentResponse(List<Equipment> equipmentList) {
        return equipmentList.stream().map(this::toEquipmentResponse).collect(Collectors.toList());
    }
}
