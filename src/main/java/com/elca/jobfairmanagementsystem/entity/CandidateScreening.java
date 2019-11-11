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
    private Long CandidateScreeningId;

    @Temporal(TemporalType.DATE)
    @Column(name = "interview_date")
    private Date InterviewDate;

    @Column(name = "position_job")
    private String PositionJob;

    @Column(name = "interviewer_name")
    private String InterviewerName;

    @Column(name = "interviewer_feedback")
    private String InterviewerFeedback;

    @Column(name = "screening_status")
    private String ScreeningStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

}
