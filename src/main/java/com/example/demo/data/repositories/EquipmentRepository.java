package com.example.demo.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.data.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query(value = "SELECT e FROM Equipment as e WHERE e.isDeleted = false")
    List<Equipment> findAllByIsDeletedFalse();
}
