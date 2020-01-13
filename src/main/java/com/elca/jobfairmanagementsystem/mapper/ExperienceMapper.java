package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.entity.Experience;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    Experience experienceDtoToEntity(ExperienceDto experienceDto);
    ExperienceDto experienceEntityToDto(Experience experience);
}

