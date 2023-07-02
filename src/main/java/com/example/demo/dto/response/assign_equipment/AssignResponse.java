package com.example.demo.dto.response.assign_equipment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AssignResponse {
    private long id;
    private String equipmentName;
    private String userName;
}
