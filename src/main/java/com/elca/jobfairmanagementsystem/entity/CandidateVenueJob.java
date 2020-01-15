package com.elca.jobfairmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "candidate_venue_job")
public class CandidateVenueJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_venue_job_id")
    private Long candidateVenueJob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_job_id")
    private VenueJob venueJob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "job_priority")
    private String jobPriority;
}
