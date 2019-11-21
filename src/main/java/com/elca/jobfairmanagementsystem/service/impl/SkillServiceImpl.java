package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.entity.Skill;
import com.elca.jobfairmanagementsystem.mapper.SkillMapper;
import com.elca.jobfairmanagementsystem.repository.SkillRepository;
import com.elca.jobfairmanagementsystem.service.SkillService;
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

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper)
    {
        this.skillRepository=skillRepository;
        this.skillMapper= skillMapper;
    }
    @Override
    public List<SkillDto> searchAllSkills() {
        List<Skill> skills = skillRepository.findAll();

        if (skills.size() != 0) {
            return skills.stream().map(skillMapper::skillEntityToDto).collect(Collectors.toList());
        }
        else{
            return null;
        }
    }

    @Override
    public SkillDto findSkillById(long skillId) {
        Optional<Skill> optionalSkill =skillRepository.findById(skillId);
        var skill = optionalSkill.orElse(null);
        return skillMapper.skillEntityToDto(skill);
    }


    @Override
    public Skill saveSkill(SkillDto skillDto) {
        Skill newSkill = skillMapper.skillDtoToEntity(skillDto);
        return skillRepository.save(newSkill);
    }

    @Override
    public void deleteSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }

    @Override
    public void updateSkill(SkillDto skillDto) {
        var updateSkill = findSkillById(skillDto.getSkillId());
        updateSkill.setSkillName(skillDto.getSkillName());
        skillRepository.save(skillMapper.skillDtoToEntity(updateSkill));
    }
}
