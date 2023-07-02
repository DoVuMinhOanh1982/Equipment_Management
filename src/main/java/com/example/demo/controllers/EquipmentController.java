package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.equipment.CreateEquipmentRequest;
import com.example.demo.dto.request.equipment.UpdateEquipmentRequest;
import com.example.demo.dto.response.equipment.EquipmentResponse;
import com.example.demo.services.equipment.EquipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<EquipmentResponse> createNewEquipment(
            @Valid @RequestBody CreateEquipmentRequest createEquipmentRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.createEquipment(createEquipmentRequest));
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResponse> getCategoryById(@PathVariable Long equipmentId) {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getEquipmentById(equipmentId));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponse>> getAllEquipments() {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAllEquipments());
    }

    @PatchMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResponse> updateEquipmentInformation(
            @Valid @RequestBody UpdateEquipmentRequest updateEquipmentRequest,
            @PathVariable Long equipmentId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.updateEquipment(equipmentId, updateEquipmentRequest));
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long equipmentId) {
        equipmentService.deleteEquipment(equipmentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
