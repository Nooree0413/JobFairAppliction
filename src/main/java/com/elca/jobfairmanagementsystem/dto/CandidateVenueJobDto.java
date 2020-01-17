package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class CandidateVenueJobDto {
    private Long candidateVenueJob;
    private VenueJobDto venueJob;
    private CandidateDto candidate;
    private String jobPriority;
    private Integer totalElements;
    private Integer totalPages;
}
