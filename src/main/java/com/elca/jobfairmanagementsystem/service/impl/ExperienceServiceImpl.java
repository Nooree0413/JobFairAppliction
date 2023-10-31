package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.entity.Experience;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.ExperienceNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.ExperienceMapper;
import com.elca.jobfairmanagementsystem.repository.ExperienceRepository;
import com.elca.jobfairmanagementsystem.service.ExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceMapper experienceMapper;
    private final ExperienceRepository experienceRepo;

    public ExperienceServiceImpl(ExperienceMapper experienceMapper, ExperienceRepository experienceRepo) {
        this.experienceMapper = experienceMapper;
        this.experienceRepo = experienceRepo;
    }

    @Override
    public List<ExperienceDto> findAllExperience() throws ExperienceNotFoundException {
        List<Experience> experiences = experienceRepo.findAll();
        if (experiences.size() != 0) {
            return experiences.stream()
                    .map(experienceMapper::experienceEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new ExperienceNotFoundException(ErrorMessages.NO_EXPERIENCE_AVAILABLE.toString());
        }
    }

    @Override
    public void saveExperience(ExperienceDto experienceDto) {
        Experience experience = experienceMapper.experienceDtoToEntity(experienceDto);
        experienceRepo.save(experience);
    }

    @Override
    public void deleteExperience(Long experienceId) throws ExperienceNotFoundException {
        ExperienceDto experience = findByExperienceId(experienceId);
        if (experience != null) {
            experienceRepo.deleteById(experienceId);
        } else {
            throw new ExperienceNotFoundException(ErrorMessages.EXPERIENCE_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateExperience(ExperienceDto experienceDto) throws ExperienceNotFoundException {
        ExperienceDto experience = findByExperienceId(experienceDto.getExperienceId());
        if (experience != null) {
            experience.setCandidateId(experienceDto.getCandidateId());
            experience.setCompanyName(experienceDto.getCompanyName());
            experience.setDuration(experienceDto.getDuration());
            experience.setPosition(experienceDto.getPosition());
            experienceRepo.save(experienceMapper.experienceDtoToEntity(experience));
        } else
            throw new ExperienceNotFoundException(ErrorMessages.EXPERIENCE_NOT_FOUND.toString());
    }

    @Override
    public ExperienceDto findByExperienceId(Long experienceId) throws ExperienceNotFoundException {
        Optional<Experience> optionalExperience = experienceRepo.findById(experienceId);
        var experience = optionalExperience.orElseThrow(() -> new ExperienceNotFoundException(ErrorMessages.EXPERIENCE_NOT_FOUND.toString()));
        return experienceMapper.experienceEntityToDto(experience);
    }
}
