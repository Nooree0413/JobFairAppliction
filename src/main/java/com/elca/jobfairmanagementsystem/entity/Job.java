package com.elca.jobfairmanagementsystem.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ghr
 */
@Setter
@Getter
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column
    private String description;

    @Column(name = "minimum_experience")
    private String minimumExperience;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "venue_job",
            joinColumns = { @JoinColumn(name = "job_id") },
            inverseJoinColumns = { @JoinColumn(name = "venue_id") })
    private Set<Venue> venues = new HashSet<>();
}
