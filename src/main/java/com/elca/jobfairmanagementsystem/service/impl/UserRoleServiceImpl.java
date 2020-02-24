package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.dto.UserRoleDto;
import com.elca.jobfairmanagementsystem.entity.User;
import com.elca.jobfairmanagementsystem.entity.UserRole;
import com.elca.jobfairmanagementsystem.mapper.UserRoleMapper;
import com.elca.jobfairmanagementsystem.repository.UserRepository;
import com.elca.jobfairmanagementsystem.repository.UserRoleRepository;
import com.elca.jobfairmanagementsystem.service.UserRoleService;
import com.elca.jobfairmanagementsystem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUserRole(UserRoleDto userRoleDto) {
        UserDto userDto = new UserDto();
        userDto.setVisa(userRoleDto.getUser().getVisa());
        userDto.setPassword(userRoleDto.getUser().getPassword());
        userDto.setActive(userRoleDto.getUser().getActive());
        userService.saveUser(userDto);
        var user = userService.loadUserByVisa(userRoleDto.getUser().getVisa());
        userRoleDto.setUser(user);
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
