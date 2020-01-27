package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.UserRoleDto;
import com.elca.jobfairmanagementsystem.entity.UserRole;
import com.elca.jobfairmanagementsystem.mapper.UserRoleMapper;
import com.elca.jobfairmanagementsystem.repository.UserRoleRepository;
import com.elca.jobfairmanagementsystem.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void saveUserRole(UserRoleDto userRoleDto) {
        UserRole userRole = userRoleMapper.userRoleDtoToEntity(userRoleDto);
        userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRoleDto> getAllUserRole() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream().map(userRoleMapper::userRoleEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserRoleDto getUserRoleByVisa(String visa) {
        UserRole userRole = userRoleRepository.getUserRoleByVisa(visa);
        return userRoleMapper.userRoleEntityToDto(userRole);
    }
}
