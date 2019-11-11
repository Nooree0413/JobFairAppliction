package com.elca.jobfairmanagementsystem.dto;

import com.elca.jobfairmanagementsystem.entity.Skill;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    private Set<SkillDto> skills = new HashSet<>();
}
