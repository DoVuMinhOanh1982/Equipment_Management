package com.example.demo.data.entities;

import java.util.List;

import com.example.demo.data.constants.EStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipment_tbl")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "equipment_name")
    private String name;

    @Column(name = "equipment_code")
    private String equipmentCode;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssignEquipment> assignEquipments;
}
