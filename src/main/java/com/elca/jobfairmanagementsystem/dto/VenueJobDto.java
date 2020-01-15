package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VenueJobDto {
    private Long venueJobId;
    private VenueDto venue;
    private JobDto job;
    private Date venueJobDate;
}
