package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "venue_job")
public class VenueJob {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "venue_job_id")
    private Long venueJobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "venue_job_date")
    private Date venueJobDate;

}
