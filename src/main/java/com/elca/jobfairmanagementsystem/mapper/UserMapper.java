package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToEntity(UserDto userDto);
    @Mapping(target = "password", ignore = true)
    UserDto userEntityToDto(User user);
}
