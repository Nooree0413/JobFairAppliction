package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class UserRoleDto {
    private Long userRoleId;
    private UserDto userDto;
    private RoleDto roleDto;
}
