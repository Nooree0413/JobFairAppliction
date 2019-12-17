package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ghr
 */
@Data
public class CandidateDto {
    private Long candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private int telNumber;
    private int mobileNumber;
    private String gender;
    private String address;
    private String nationality;
    private Date registrationDate;
    private Date availabilityDate;
    private String currentLevel;
    private String jobType;
    private String currentAcademicYear;
    private List<ExperienceDto> experienceDtos;
    private List<QualificationDto> qualificationDtos;
    private List<CandidateSkillDto> candidateSkillDtos;
    private List<CandidateVenueJobSaveDto> candidateVenueJobSaveDto;
}
