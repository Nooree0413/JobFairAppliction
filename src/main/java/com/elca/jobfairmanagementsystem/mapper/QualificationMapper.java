package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QualificationMapper {
    Qualification qualificationDtoToEntity(QualificationDto qualificationDto);
    QualificationDto qualificationEntityToDto(Qualification qualification);
}
