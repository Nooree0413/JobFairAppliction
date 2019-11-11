package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    Skill skillDtoToEntity(SkillDto skillDto);

    SkillDto skillEntityToDto(Skill skill);
}
