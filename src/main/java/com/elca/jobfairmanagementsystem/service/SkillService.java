package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.dto.SkillPaginationDto;
import com.elca.jobfairmanagementsystem.exception.SkillNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bfk
 */
public interface SkillService {
    SkillPaginationDto findAllSkills(Pageable pageable) throws SkillNotFoundException;

    SkillDto findSkillById(long skillId) throws SkillNotFoundException;

    void saveSkill(SkillDto skillDto);

    void deleteSkill(Long skillId) throws SkillNotFoundException;

    void updateSkill(SkillDto skillDto) throws SkillNotFoundException;
}
