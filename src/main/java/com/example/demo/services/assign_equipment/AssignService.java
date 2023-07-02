package com.example.demo.services.assign_equipment;

import java.util.List;

import com.example.demo.dto.request.assign_equipment.CreateAssignRequest;
import com.example.demo.dto.response.assign_equipment.AssignResponse;

public interface AssignService {
    AssignResponse createAssign(CreateAssignRequest createAssignRequest);

    void deleteAssign(Long assignId);

    List<AssignResponse> getAllAssignsByUser();

    List<AssignResponse> getAllAssigns();
}
