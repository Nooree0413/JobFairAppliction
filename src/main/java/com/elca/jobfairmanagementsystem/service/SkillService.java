package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.entity.Skill;

import java.util.List;

/**
 * @author bfk
 */
public interface SkillService {
    List<SkillDto> searchAllSkills();

    SkillDto findSkillById(long skillId);

    Skill saveSkill(SkillDto skillDto);

    void deleteSkill(Long skillId);

    void updateSkill(SkillDto skillDto);
}
