package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class CandidateVenueJobSaveDto {
    private Long candidateVenueJob;
    private long venueId;
    private long jobId;
    private long candidateId;
    private String jobPriority;
}
