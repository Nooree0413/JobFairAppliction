package com.elca.jobfairmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
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
