package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ghr
 */
@Data
@NoArgsConstructor
public class CandidateScreeningDto {
    private Long candidateScreeningId;
    private Date interviewDate;
    private String interviewerName;
    private String interviewerFeedback;
    private String screeningStatus;
    private Long candidateId;
}
