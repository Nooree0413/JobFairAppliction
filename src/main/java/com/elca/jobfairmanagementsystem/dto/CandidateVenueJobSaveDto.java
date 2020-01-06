package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class CandidateVenueJobSaveDto {
    private Long candidateVenueJob;
    private Long venueId;
    private Long jobId;
    private Long candidateId;
    private Long venueJobId;
    private String jobPriority;
    private List<JobDto> jobList;
}
