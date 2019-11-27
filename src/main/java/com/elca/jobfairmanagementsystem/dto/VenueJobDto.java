package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VenueJobDto {
    private Long venueJobId;
    private Long venueId;
    private Long jobId;
}
