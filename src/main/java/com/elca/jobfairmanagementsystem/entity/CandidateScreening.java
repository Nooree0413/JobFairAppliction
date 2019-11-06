package com.elca.jobfairmanagementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column
    private Data InterviewDate;

    @Column
    private String PositionJob;

    @Column
    private String InterviewerName;

    @Column
    private String InterviewerFeedback;

    @Column
    private String ScreeningStatus;


}
