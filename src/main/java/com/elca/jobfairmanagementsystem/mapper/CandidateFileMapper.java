package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.entity.CandidateFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateFileMapper {
    CandidateFileDto candidateFileEntityToDto(CandidateFile candidateFile);
}
