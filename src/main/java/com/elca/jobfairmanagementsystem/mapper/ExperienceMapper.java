package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    @Mapping(target = "candidate.candidateId", source = "experienceDto.candidateId")
    Experience experienceDtoToEntity(ExperienceDto experienceDto);

    @Mapping(target = "candidateId", source = "experience.candidate.candidateId")
    ExperienceDto experienceEntityToDto(Experience experience);
}

