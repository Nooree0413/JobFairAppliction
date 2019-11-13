package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.exception.ExperienceNotFoundException;
import com.elca.jobfairmanagementsystem.exception.NoContentException;

import java.util.List;

public interface ExperienceService {

    List<ExperienceDto> searchAllExperience() throws NoContentException;

    ExperienceDto findByExperienceId(Long experienceId) throws ExperienceNotFoundException;

    void saveExperience(ExperienceDto experienceDto);

    void deleteExperience(Long experienceId);

    void updateExperience(ExperienceDto experienceDto) throws ExperienceNotFoundException;

}
