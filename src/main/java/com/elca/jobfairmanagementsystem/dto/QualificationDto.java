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

}
