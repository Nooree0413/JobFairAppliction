package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ghr
 */
@Data
@NoArgsConstructor
public class CandidateScreeningDto {
    private Long CandidateScreeningId;
    private Data InterviewDate;
    private String PositionJob;
    private String InterviewerName;
    private String InterviewerFeedback;
    private String ScreeningStatus;
    private Long jobId;
    private Long candidateDto;
}
