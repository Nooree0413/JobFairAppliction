package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.UserRoleDto;

import java.util.List;

public interface UserRoleService {
    void saveUserRole(UserRoleDto userRoleDto);
    List<UserRoleDto> getAllUserRole();
    UserRoleDto getUserRoleByVisa(String visa);
}
