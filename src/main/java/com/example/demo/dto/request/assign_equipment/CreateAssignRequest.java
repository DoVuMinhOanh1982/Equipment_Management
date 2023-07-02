package com.example.demo.dto.request.assign_equipment;

import lombok.*;

@Getter
@Setter
@Builder
public class CreateAssignRequest {
    private Long equipmentId;
    private String username;
}
