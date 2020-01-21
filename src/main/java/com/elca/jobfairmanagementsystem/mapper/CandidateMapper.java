package com.elca.jobfairmanagementsystem.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import org.mapstruct.Mapping;

/**
 * @author ghr
 */
@Mapper(componentModel = "spring", uses = {CandidateVenueJobMapper.class})
public interface CandidateMapper {

    @Mapping(target = "experienceDtos", source = "candidateEntity.experiences")
    @Mapping(target = "qualificationDtos", source = "candidateEntity.qualifications")
    @Mapping(target = "candidateVenueJobSaveDto", source = "candidateVenueJobs")
    @Mapping(target = "candidateSkillDtos", source = "candidateEntity.candidateSkills")
    @Mapping(target = "candidateScreeningDtos", source = "candidateEntity.candidateScreenings")
    @Mapping(target = "candidateFileDtos", source = "candidateEntity.candidateFiles")
    CandidateDto candidateEntityToCandidateDto(Candidate candidateEntity);

    @InheritInverseConfiguration
    Candidate candidateDtoToCandidateEntity(CandidateDto candidateDto);
}
