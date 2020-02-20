package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author ghr
 */
@Mapper(componentModel = "spring")
public interface CandidateScreeningMapper {
    @Mapping(target = "candidate.candidateId", source = "candidateScreeningDto.candidateId")
    CandidateScreening candidateScreeningDtoToEntity(CandidateScreeningDto candidateScreeningDto);

    @Mapping(target = "candidateId", source = "candidateScreening.candidate.candidateId")
    CandidateScreeningDto candidateScreeningEntityToDto(CandidateScreening candidateScreening);
}
