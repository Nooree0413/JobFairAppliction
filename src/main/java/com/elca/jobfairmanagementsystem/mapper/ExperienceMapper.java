package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    Experience experienceDtoToEntity(ExperienceDto experienceDto);
    ExperienceDto experienceEntityToDto(Experience experience);
}

