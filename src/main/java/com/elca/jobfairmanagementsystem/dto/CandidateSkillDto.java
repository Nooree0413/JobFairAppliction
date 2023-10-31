package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class CandidateSkillDto {
    private Long candidateSkillId;
    private Long candidateId;
    private SkillDto skill;
    private boolean checked;
}
