package com.elca.jobfairmanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "interview_date")
    @Temporal(TemporalType.DATE)
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
