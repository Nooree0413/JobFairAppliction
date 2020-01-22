package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.dto.SkillPaginationDto;
import com.elca.jobfairmanagementsystem.entity.Skill;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.SkillNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.SkillMapper;
import com.elca.jobfairmanagementsystem.repository.SkillRepository;
import com.elca.jobfairmanagementsystem.service.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author bfk
 */

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillPaginationDto findAllSkills(Pageable pageable) throws SkillNotFoundException {
        Page<Skill> skills = skillRepository.findAll(pageable);
        if(skills == null){
            throw new SkillNotFoundException(ErrorMessages.SKILL_NOT_FOUND.toString());
        } else {
            List<SkillDto> skillDto = skills.stream().map(skillMapper::skillEntityToDto).collect(Collectors.toList());
            SkillPaginationDto skillPaginationDto = new SkillPaginationDto();
            skillPaginationDto.setSkillDtoList(skillDto);
            skillPaginationDto.setTotalElements(skills.getNumberOfElements());
            skillPaginationDto.setTotalPages(skills.getTotalPages());
            return skillPaginationDto;
        }
    }

    @Override
    public SkillDto findSkillById(long skillId) throws SkillNotFoundException {
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        var skill = optionalSkill.orElseThrow(() -> new SkillNotFoundException(ErrorMessages.SKILL_NOT_FOUND.toString()));
        return skillMapper.skillEntityToDto(skill);
    }

    @Override
    public void saveSkill(SkillDto skillDto) {
        Skill newSkill = skillMapper.skillDtoToEntity(skillDto);
        skillRepository.save(newSkill);
    }

    @Override
    public void deleteSkill(Long skillId) throws SkillNotFoundException {
        var getSkill = findSkillById(skillId);
        if (getSkill != null) {
            skillRepository.deleteById(skillId);
        } else {
            throw new SkillNotFoundException(ErrorMessages.SKILL_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateSkill(SkillDto skillDto) throws SkillNotFoundException {
        var updateSkill = findSkillById(skillDto.getSkillId());
        if (updateSkill != null) {
            updateSkill.setSkillName(skillDto.getSkillName());
            skillRepository.save(skillMapper.skillDtoToEntity(updateSkill));
        } else {
            throw new SkillNotFoundException(ErrorMessages.SKILL_NOT_FOUND.toString());
        }
    }
}
