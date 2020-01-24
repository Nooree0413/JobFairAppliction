package com.elca.jobfairmanagementsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "candidate_add")
    private Boolean candidateAdd;

    @Column(name = "candidate_detail")
    private Boolean candidateDetail;

    @Column(name = "candidate_list")
    private Boolean candidateList;

    @Column
    private Boolean home;

    @Column(name = "job_list")
    private Boolean jobList;

    @Column(name = "venue_page")
    private Boolean venuePage;

    @Column
    private Boolean dashboard;

    @Column(name = "job_bo")
    private Boolean jobBo;

    @Column(name = "skill_bo")
    private Boolean skillBo;

    @Column(name = "venue_bo")
    private Boolean venueBo;

    @Column(name = "venue_job_bo")
    private Boolean VenueJobBo;
}
