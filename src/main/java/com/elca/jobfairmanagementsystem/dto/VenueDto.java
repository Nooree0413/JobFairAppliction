package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class VenueDto {

    private Long venueId;

    private String venueName;

    private Date startDate;

    private Date endDate;

    private boolean status;
}
