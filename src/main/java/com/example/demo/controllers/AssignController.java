package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.assign_equipment.CreateAssignRequest;
import com.example.demo.dto.response.assign_equipment.AssignResponse;
import com.example.demo.services.assign_equipment.AssignService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/assign")
public class AssignController {
    @Autowired
    private AssignService assignService;

    @PostMapping
    public ResponseEntity<AssignResponse> createAssign(
            @Valid @RequestBody CreateAssignRequest createAssignRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assignService.createAssign(createAssignRequest));
    }

    @DeleteMapping("/{assignId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long assignId) {
        assignService.deleteAssign(assignId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<AssignResponse>> getAllAssignsByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(assignService.getAllAssignsByUser());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssignResponse>> getAllAssigns() {
        return ResponseEntity.status(HttpStatus.OK).body(assignService.getAllAssigns());
    }
}
