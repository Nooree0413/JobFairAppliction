package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.RoleDto;
import com.elca.jobfairmanagementsystem.entity.Role;
import com.elca.jobfairmanagementsystem.mapper.RoleMapper;
import com.elca.jobfairmanagementsystem.repository.RoleRepository;
import com.elca.jobfairmanagementsystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(RoleDto roleDto) {
        Role role = roleMapper.roleDtoToEntity(roleDto);
        roleRepository.save(role);
    }

    @Override
    public RoleDto getRoleDetailsByRoleName(String roleName) {
        Role role = roleRepository.findByName(roleName);
        RoleDto roleDto = roleMapper.roleEntityToDto(role);
        return roleDto;
    }
}
