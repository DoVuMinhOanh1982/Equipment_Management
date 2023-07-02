package com.example.demo.data.entities;

import java.util.List;

import com.example.demo.data.constants.EUser;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EUser type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "userAssigned", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssignEquipment> assigned;
}
