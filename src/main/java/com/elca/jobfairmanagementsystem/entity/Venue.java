package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "venue")
public class Venue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "venue_id")
    private Long venueId;

    @Column(name = "venue_name")
    private String venueName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private boolean status;
}
