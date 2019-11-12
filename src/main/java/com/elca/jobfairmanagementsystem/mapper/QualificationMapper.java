package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QualificationMapper {

    Qualification qualificationDtoToEntity(QualificationDto qualificationDto);

    QualificationDto qualificationEntityToDto(Qualification qualification);
}
