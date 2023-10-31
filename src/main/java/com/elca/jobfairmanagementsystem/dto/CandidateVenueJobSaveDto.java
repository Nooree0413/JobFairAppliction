package com.elca.jobfairmanagementsystem.dto;

import com.elca.jobfairmanagementsystem.entity.VenueJob;
import lombok.Data;

import java.util.List;

@Data
public class CandidateVenueJobSaveDto {
    private Long candidateVenueJob;
    private VenueDto venueId;
    private Long jobId;
    private Long candidateId;
    private Long venueJobId;
    private String jobPriority;
    private List<JobDto> jobList;
}
