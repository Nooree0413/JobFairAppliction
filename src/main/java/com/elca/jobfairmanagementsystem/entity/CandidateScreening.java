package com.elca.jobfairmanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 *
 * @author ghr
 */
@Data
@Entity
@Table(name = "candidate_screening")
public class CandidateScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_screening_id")
    private Long candidateScreeningId;

    @Temporal(TemporalType.DATE)
    @Column(name = "interview_date")
    private Date interviewDate;

    @Column(name = "interviewer_name")
    private String interviewerName;

    @Column(name = "interviewer_feedback")
    private String interviewerFeedback;

    @Column(name = "screening_status")
    private String screeningStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
