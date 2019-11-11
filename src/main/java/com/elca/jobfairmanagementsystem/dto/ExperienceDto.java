package com.elca.jobfairmanagementsystem.dto;

import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExperienceDto {
    private Long experienceId;

    private String companyName;

    private String position;

    private String duration;

    private String leavingReason;

    private Candidate candidate;
 }
