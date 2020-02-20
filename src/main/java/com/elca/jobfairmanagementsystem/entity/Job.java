package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
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

    @Column
    private String title;

    @Column
    private String level;

    @Column
    private String category;

    @Column
    private String description;

    @Column(name = "minimum_experience")
    private String minimumExperience;

    @Column(name = "qualification_needed")
    private String qualificationNeeded;

}
