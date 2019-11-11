package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class VenueDto {

    private Long venueId;

    private String venueName;

    private Date startDate;

    private Date endDate;

    private boolean status;

    private Set<JobDto> jobs = new HashSet<>();
}
