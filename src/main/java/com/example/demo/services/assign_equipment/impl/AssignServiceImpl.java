package com.example.demo.services.assign_equipment.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.constants.EStatus;
import com.example.demo.data.entities.AssignEquipment;
import com.example.demo.data.entities.Equipment;
import com.example.demo.data.entities.User;
import com.example.demo.data.repositories.AssignRepository;
import com.example.demo.data.repositories.EquipmentRepository;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.dto.request.assign_equipment.CreateAssignRequest;
import com.example.demo.dto.response.assign_equipment.AssignResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.services.assign_equipment.AssignService;
import com.example.demo.services.user.SecurityContextService;

import lombok.Builder;

@Service
@Builder
public class AssignServiceImpl implements AssignService {
    @Autowired
    private AssignRepository assignRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityContextService securityContextService;

    @Override
    public AssignResponse createAssign(CreateAssignRequest createAssignRequest) {
        Optional<Equipment> equipment = equipmentRepository.findById(createAssignRequest.getEquipmentId());
        if (!equipment.isPresent()) {
            throw new BadRequestException("Equipment not found");
        }
        if (equipment.get().isDeleted()) {
            throw new BadRequestException("Equipment is deleted");
        }
        if (equipment.get().getStatus().equals(EStatus.NOT_AVAILABLE)) {
            throw new BadRequestException("Equipment is assigned");
        }

        Optional<User> user = userRepository.findByUsername(createAssignRequest.getUsername());
        if (!user.isPresent()) {
            throw new BadRequestException("User not found");
        }
        if (user.get().isDeleted()) {
            throw new BadRequestException("User is deleted");
        }
        AssignEquipment assignEquipment = AssignEquipment.builder()
                .equipment(equipment.get())
                .userAssigned(user.get())
                .isDeleted(false)
                .build();

        equipment.get().setStatus(EStatus.NOT_AVAILABLE);
        equipmentRepository.save(equipment.get());

        assignRepository.save(assignEquipment);
        return AssignResponse.builder()
                .id(assignEquipment.getId())
                .equipmentName(assignEquipment.getEquipment().getName())
                .userName(assignEquipment.getUserAssigned().getUsername())
                .build();

    }

    @Override
    public void deleteAssign(Long assignId) {
        Optional<AssignEquipment> assignEquipment = assignRepository.findById(assignId);
        if (!assignEquipment.isPresent()) {
            throw new BadRequestException("Assign not found");
        }

        Optional<Equipment> equipment = equipmentRepository.findById(assignEquipment.get().getEquipment().getId());
        if (!equipment.isPresent()) {
            throw new BadRequestException("Equipment not found");
        }
        if (equipment.get().isDeleted()) {
            throw new BadRequestException("Equipment is deleted");
        }

        equipment.get().setStatus(EStatus.AVAILABLE);
        equipmentRepository.save(equipment.get());
        assignEquipment.get().setDeleted(true);
        assignRepository.save(assignEquipment.get());
    }

    @Override
    public List<AssignResponse> getAllAssignsByUser() {
        Optional<User> user = userRepository.findByUsername(securityContextService.getCurrentUser().getUsername());
        if (!user.isPresent()) {
            throw new BadRequestException("User not found");
        }
        
        if (user.get().isDeleted()) {
            throw new BadRequestException("User is deleted");
        }

        List<AssignEquipment> assignEquipments = assignRepository
                .findByUserAssignedAndIsDeletedFalse(user.get().getUsername());

        return assignEquipments.stream().map(assignEquipment -> AssignResponse.builder()
                .id(assignEquipment.getId())
                .equipmentName(assignEquipment.getEquipment().getName())
                .userName(assignEquipment.getUserAssigned().getUsername())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<AssignResponse> getAllAssigns() {
        return assignRepository.findAllByIsDeletedFalse().stream().map(assignEquipment -> AssignResponse.builder()
                .id(assignEquipment.getId())
                .equipmentName(assignEquipment.getEquipment().getName())
                .userName(assignEquipment.getUserAssigned().getUsername())
                .build()).collect(Collectors.toList());
    }

}
