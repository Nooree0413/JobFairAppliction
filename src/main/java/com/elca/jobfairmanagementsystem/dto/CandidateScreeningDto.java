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
    private JobDto jobDto;
    private CandidateDto candidateDto;

    @JsonProperty("jobId")
    private Long getJobId() {
        if (jobDto == null) {
            return null;
        }
        return jobDto.getJobId();
    }

    @JsonProperty("jobId")
    private void setJobId(long jobId) {
        jobDto = new JobDto();
        jobDto.setJobId(jobId);
    }

    @JsonProperty("candidateId")
    private Long getCandidateId() {
        if (candidateDto == null) {
            return null;
        }
        return candidateDto.getCandidateId();
    }

    @JsonProperty("candidateId")
    private void setCandidateId(long candidateId) {
        candidateDto = new CandidateDto();
        candidateDto.setCandidateId(candidateId);
    }
}
