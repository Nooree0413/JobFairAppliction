package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String visa;
    private String password;
    private Boolean active;
}
