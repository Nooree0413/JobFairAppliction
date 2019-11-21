package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateSkillMapper {
    @Mapping(target = "candidate.candidateId", source = "candidateSkillDto.candidateId")
    @Mapping(target = "skill.skillId", source = "candidateSkillDto.skillId")
    CandidateSkill candidateSkillDtoToEntity (CandidateSkillDto candidateSkillDto);

    @Mapping(target = "skillId", source = "candidateSkill.skill.skillId")
    @Mapping(target = "candidateId", source = "candidateSkill.candidate.candidateId")
    CandidateSkillDto candidateSkillEntityToDto (CandidateSkill candidateSkill);
}
