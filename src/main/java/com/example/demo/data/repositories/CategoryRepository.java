package com.example.demo.data.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findById(int id);

    @Query(value = "SELECT c FROM Category as c WHERE c.prefixEquipmentCode = :prefixEquipmentCode")
    Optional<Category> findByPrefixEquipmentCode(@Param("prefixEquipmentCode") String prefixEquipmentCode);

    @Query(value = "SELECT c FROM Category as c WHERE c.isDeleted = false")
    List<Category> findAllByIsDeletedFalse();
}
