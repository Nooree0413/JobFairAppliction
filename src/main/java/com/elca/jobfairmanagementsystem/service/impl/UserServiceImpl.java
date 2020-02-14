package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.entity.User;
import com.elca.jobfairmanagementsystem.mapper.UserMapper;
import com.elca.jobfairmanagementsystem.repository.UserRepository;
import com.elca.jobfairmanagementsystem.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = userMapper.userDtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto loadUserByVisa(String visa) throws UsernameNotFoundException {
        User user = userRepository.findByVisa(visa);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return userMapper.userEntityToDto(user);
    }

    @Override
    public List<UserDto> listOfUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userEntityToDto).collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User employee = userRepository.findByVisa(username);
        if(employee == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employee.getVisa(), employee.getPassword(), getAuthority());
    }
}
