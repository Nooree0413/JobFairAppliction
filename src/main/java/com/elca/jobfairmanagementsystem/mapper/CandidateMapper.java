package com.elca.jobfairmanagementsystem.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import org.mapstruct.Mapping;

/**
 * @author ghr
 */
@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(target = "experienceDtos", source = "candidateEntity.experiences")
    @Mapping(target = "qualificationDtos", source = "candidateEntity.qualifications")
    @Mapping(target = "candidateVenueJobSaveDto", source = "candidateVenueJobs")
    @Mapping(target = "candidateSkillDtos", source = "candidateEntity.candidateSkills")
    CandidateDto candidateEntityToCandidateDto(Candidate candidateEntity);
//
//    @Mapping(target = "experiences", source = "candidateDto.experienceDtos")
//    @Mapping(target = "qualifications", source = "candidateDto.qualificationDtos")
//    @Mapping(target = "candidateSkills", source = "candidateDto.candidateSkillDtos")
//    @Mapping(target = "candidateVenueJobs", source = "candidateDto.candidateVenueJobSaveDto")
//    @Mapping(target = "candidateSkills.skill.skillId", source = "candidateDto.candidateSkillDtos.skillId.skillId")
    @InheritInverseConfiguration
    Candidate candidateDtoToCandidateEntity(CandidateDto candidateDto);
}
