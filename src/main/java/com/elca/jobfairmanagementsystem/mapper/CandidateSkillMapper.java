package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateSkillMapper {
    CandidateSkill candidateSkillDtoToEntity(CandidateSkillDto candidateSkillDto);

    CandidateSkillDto candidateSkillEntityToDto(CandidateSkill candidateSkill);
}
