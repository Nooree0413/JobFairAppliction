package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author ghr
 */
@Data
@NoArgsConstructor
public class CandidateScreeningDto {
    private Long CandidateScreeningId;
    private Date InterviewDate;
    private String PositionJob;
    private String InterviewerName;
    private String InterviewerFeedback;
    private String ScreeningStatus;
    private Long venueJobId;
    private Long candidateId;
}
