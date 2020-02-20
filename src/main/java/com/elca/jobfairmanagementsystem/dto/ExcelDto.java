package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExcelDto {

    private LocalDateTime registrationDate;
    private String firstName;
    private String lastName;
    private String venueName;

    public ExcelDto(LocalDateTime registrationDate, String firstName, String lastName, String venueName) {
        this.registrationDate = registrationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.venueName = venueName;
    }
}
