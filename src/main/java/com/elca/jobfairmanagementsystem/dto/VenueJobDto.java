package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class VenueJobDto {
    private Long venueJobId;
    private VenueDto venue;
    private JobDto job;
}
