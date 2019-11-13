package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QualificationMapper {

    @Mapping(target = "candidate.candidateId", source = "qualificationDto.candidateId")
    Qualification qualificationDtoToEntity(QualificationDto qualificationDto);

    @Mapping(target = "candidateId", source = "qualification.candidate.candidateId")
    QualificationDto qualificationEntityToDto(Qualification qualification);
}
