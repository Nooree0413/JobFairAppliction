package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class CandidateSkillDto {
    private Long candidateSkillId;
    private Long candidateId;
    private SkillDto skillId;
    private boolean checked;
}
