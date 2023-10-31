package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.exception.ExperienceNotFoundException;

import java.util.List;

public interface ExperienceService {

    List<ExperienceDto> findAllExperience() throws ExperienceNotFoundException;

    ExperienceDto findByExperienceId(Long experienceId) throws ExperienceNotFoundException;

    void saveExperience(ExperienceDto experienceDto);

    void deleteExperience(Long experienceId) throws  ExperienceNotFoundException;

    void updateExperience(ExperienceDto experienceDto) throws ExperienceNotFoundException;

}
