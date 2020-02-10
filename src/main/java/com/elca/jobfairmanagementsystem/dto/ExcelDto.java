package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class ExcelDto {
    private Long candidateId;
    private String firstName;
    private String lastName;

    public ExcelDto(Long candidateId,String firstName,String lastName){
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
