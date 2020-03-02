package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QualificationDto {

    private Long qualificationId;

    private String title;

    private String division;

    private String institution;

    private Date graduationDate;

    private String course;

    private Long candidateId;

}
