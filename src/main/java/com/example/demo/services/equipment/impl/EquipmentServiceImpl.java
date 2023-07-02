package com.example.demo.services.equipment.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.entities.Category;
import com.example.demo.data.entities.Equipment;
import com.example.demo.data.repositories.CategoryRepository;
import com.example.demo.data.repositories.EquipmentRepository;
import com.example.demo.dto.request.equipment.CreateEquipmentRequest;
import com.example.demo.dto.request.equipment.UpdateEquipmentRequest;
import com.example.demo.dto.response.equipment.EquipmentResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.mapper.EquipmentMapper;
import com.example.demo.services.equipment.EquipmentService;

import lombok.Builder;

@Service
@Builder
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public EquipmentResponse createEquipment(CreateEquipmentRequest createEquipmentRequest) {
        Category category = categoryRepository.findById(createEquipmentRequest.getCategoryId()).get();
        if (category == null) {
            throw new BadRequestException("Category not found");
        }
        if (category.isDeleted()) {
            throw new BadRequestException("Category is deleted");
        }
        Equipment equipment = equipmentMapper.toEquipment(createEquipmentRequest);
        equipment.setCategory(category);
        equipment.setEquipmentCode(category.getPrefixEquipmentCode() + equipmentRepository.count());
        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Override
    public EquipmentResponse getEquipmentById(Long equipmentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        if (equipment == null) {
            throw new BadRequestException("Equipment not found");
        }
        if (equipment.isDeleted()) {
            throw new BadRequestException("Equipment is deleted");
        }
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Override
    public List<EquipmentResponse> getAllEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAllByIsDeletedFalse();
        return equipmentMapper.toListEquipmentResponse(equipmentList);
    }

    @Override
    public EquipmentResponse updateEquipment(Long equipmentId, UpdateEquipmentRequest updateEquipmentRequest) {
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        if (equipment == null) {
            throw new BadRequestException("Equipment not found");
        }
        if (equipment.isDeleted()) {
            throw new BadRequestException("Equipment is deleted");
        }
        equipment.setName(updateEquipmentRequest.getEquipmentName());
        equipment.setDescription(updateEquipmentRequest.getDescription());
        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Override
    public void deleteEquipment(Long equipmentId) {
        Optional<Equipment> equipmentOtp = equipmentRepository.findById(equipmentId);
        if (equipmentOtp.isEmpty()) {
            throw new BadRequestException("Equipment not found");
        }
        if (equipmentOtp.get().isDeleted()) {
            throw new BadRequestException("Equipment is deleted");
        }
        equipmentOtp.get().setDeleted(true);
        equipmentRepository.save(equipmentOtp.get());
    }

}
