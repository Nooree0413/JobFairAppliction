package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateSkillMapper {
    CandidateSkill candidateSkillDtoToEntity (CandidateSkillDto candidateSkillDto);
    CandidateSkillDto candidateSkillEntityToDto (CandidateSkill candidateSkill);
}
