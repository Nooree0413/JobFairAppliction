package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class JobDto {

    private Long jobId;

    private String title;

    private String level;

    private String category;

    private String description;

    private String  minimumExperience;

    private String qualificationNeeded;

    private Boolean checked;
}
