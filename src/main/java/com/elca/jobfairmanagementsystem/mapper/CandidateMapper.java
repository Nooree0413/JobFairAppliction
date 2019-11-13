package com.elca.jobfairmanagementsystem.mapper;

import org.mapstruct.Mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;

/**
 * @author ghr
 */
@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDto candidateEntityToCandidateDto(Candidate candidateEntity);

    Candidate candidateDtoToCandidateEntity(CandidateDto candidateDto);

}
