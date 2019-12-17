package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateSkillMapper {
    @Mapping(target = "skill.skillId", source = "candidateSkillDto.skill.skillId")
    CandidateSkill candidateSkillDtoToEntity (CandidateSkillDto candidateSkillDto);

@InheritInverseConfiguration
        CandidateSkillDto candidateSkillEntityToDto (CandidateSkill candidateSkill);
}
