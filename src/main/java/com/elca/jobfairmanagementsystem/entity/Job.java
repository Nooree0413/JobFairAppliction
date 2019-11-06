package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
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
    private String  minimumExperience;

    @Column(name = "qualification_needed")
    private String qualificationNeeded;
}
