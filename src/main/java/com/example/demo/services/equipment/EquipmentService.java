package com.example.demo.services.equipment;

import java.util.List;

import com.example.demo.dto.request.equipment.CreateEquipmentRequest;
import com.example.demo.dto.request.equipment.UpdateEquipmentRequest;
import com.example.demo.dto.response.equipment.EquipmentResponse;

public interface EquipmentService {
    EquipmentResponse createEquipment(CreateEquipmentRequest createEquipmentRequest);

    EquipmentResponse getEquipmentById(Long equipmentId);

    EquipmentResponse updateEquipment(Long equipmentId, UpdateEquipmentRequest updateEquipmentRequest);

    void deleteEquipment(Long equipmentId);

    List<EquipmentResponse> getAllEquipments();
}
