package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

/**
 *
 * @author ghr
 */
@Data
public class CandidateDto {
    private Long candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private int mobileNo;
    private int phoneNo;
    private String address;
    private String nationality;

}
