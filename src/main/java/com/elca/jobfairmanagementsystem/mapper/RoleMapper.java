package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.RoleDto;
import com.elca.jobfairmanagementsystem.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleEntityToDto(Role role);

    Role roleDtoToEntity(RoleDto roleDto);
}
