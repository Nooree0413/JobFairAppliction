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
}
