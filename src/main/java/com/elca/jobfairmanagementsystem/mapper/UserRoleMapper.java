package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.UserRoleDto;
import com.elca.jobfairmanagementsystem.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRole userRoleDtoToEntity(UserRoleDto userRoleDto);

    @Mapping(target = "user.password", ignore = true)
    UserRoleDto userRoleEntityToDto(UserRole userRole);
}
