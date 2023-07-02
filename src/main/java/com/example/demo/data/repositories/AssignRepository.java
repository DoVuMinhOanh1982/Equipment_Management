package com.example.demo.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.entities.AssignEquipment;

public interface AssignRepository extends JpaRepository<AssignEquipment, Long> {
    @Query(value = "SELECT a FROM AssignEquipment as a WHERE a.userAssigned.username = :username AND a.isDeleted = false")
    List<AssignEquipment> findByUserAssignedAndIsDeletedFalse(@Param("username") String username);

    @Query(value = "SELECT a FROM AssignEquipment as a WHERE a.isDeleted = false")
    List<AssignEquipment> findAllByIsDeletedFalse();
}
