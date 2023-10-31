package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class ExperienceDto {
    private Long experienceId;

    private String companyName;

    private String position;

    private String duration;

    private Long candidateId;
 }
