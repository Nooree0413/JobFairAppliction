package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VenueDto {

    private Long venueId;

    private String venueName;

    private Date startDate;

    private Date endDate;

    private String address;

    private boolean active;
}
