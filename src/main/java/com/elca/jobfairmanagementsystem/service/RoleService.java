package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.RoleDto;

public interface RoleService {
    void saveRole(RoleDto roleDto);
    RoleDto getRoleDetailsByRoleName(String roleName);
}
