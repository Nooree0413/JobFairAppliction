package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class JobDto {

    private Long jobId;

    private String jobTitle;

    private String description;

    private String  minimumExperience;

    private String qualificationNeeded;

    private Set<VenueDto> venues = new HashSet<>();

}
