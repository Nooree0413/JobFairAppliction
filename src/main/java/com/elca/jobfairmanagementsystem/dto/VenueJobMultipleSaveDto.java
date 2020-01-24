package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VenueJobMultipleSaveDto {
    private Long venueJobId;
    private VenueDto venue;
    private List<JobDto> job;
    private Date venueJobDate;
}
