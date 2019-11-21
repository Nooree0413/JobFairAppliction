package com.elca.jobfairmanagementsystem.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "availability_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date availabilityDate;

    @Column(name = "current_level",nullable = false)
    private String currentLevel;

    @Column(name = "job_type",nullable = false)
    private String jobType;

    @Column(name = "current_academic_year")
    private String currentAcademicYear;
}
