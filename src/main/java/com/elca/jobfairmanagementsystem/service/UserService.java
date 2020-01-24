package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    void saveUser(UserDto userDto);
    UserDto loadUserByVisa(String visa) throws UsernameNotFoundException;
}
