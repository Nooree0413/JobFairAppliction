package com.elca.jobfairmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class QualificationDto {

    private Long qualificationId;

    private String title;

    private String result;

    private String institution;

    private Date dateFrom;

    private Date dateTo;

}
