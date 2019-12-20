package com.elca.jobfairmanagementsystem.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author ghr
 */

@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "tel_number")
    private int telNumber;

    @Column(name = "mobile_number",nullable = false)
    private int mobileNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "nationality",nullable = false)
    private String nationality;

    @Column(name = "registration_date",nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "availability_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date availabilityDate;

    @Column(name = "current_level",nullable = false)
    private String currentLevel;

    @Column(name = "job_type",nullable = false)
    private String jobType;

    @Column(name = "current_academic_year")
    private String currentAcademicYear;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "candidate_id")
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "candidate_id")
    private List<Qualification> qualifications = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "candidate_id")
    private List<CandidateSkill> candidateSkills = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "candidate_id")
    private List<CandidateVenueJob> candidateVenueJobs = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "candidate_id")
    private List<CandidateScreening> candidateScreenings = new ArrayList<>();
}
