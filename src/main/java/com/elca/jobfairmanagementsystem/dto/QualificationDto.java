package com.elca.jobfairmanagementsystem.dto;

import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class QualificationDto {

    private Long qualificationId;

    private String title;

    private String result;

    private String institution;

    private Date dateFrom;

    private Date dateTo;

    private Candidate candidate;

    @JsonProperty("candidateId")
    private void setCandidateId(Long candidateId){
        candidate = new Candidate();
        candidate.setCandidateId(candidateId);
    }

    @JsonProperty
    private Long getCandidateId(){
        candidate = new Candidate();
        return candidate.getCandidateId();
    }
}
