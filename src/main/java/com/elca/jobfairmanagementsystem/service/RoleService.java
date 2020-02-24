package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.RoleDto;

import java.util.List;

public interface RoleService {
    void saveRole(RoleDto roleDto);

    RoleDto getRoleDetailsByRoleName(String roleName);

    List<RoleDto> getAllRoles();
}
