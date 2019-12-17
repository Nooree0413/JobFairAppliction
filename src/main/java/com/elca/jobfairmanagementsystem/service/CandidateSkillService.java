package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.exception.CandidateSkillNotFoundException;

import java.util.List;

public interface CandidateSkillService {
    List<CandidateSkillDto> findAllCandidateSkills() throws CandidateSkillNotFoundException;

    void saveCandidateSkill(List<CandidateSkillDto> candidateSkillDto);

    void deleteCandidateSkill(Long candidateSkillId) throws CandidateSkillNotFoundException;

    void updateCandidateSkill(CandidateSkillDto candidateSkillDto) throws CandidateSkillNotFoundException;

    CandidateSkillDto findCandidateSkillById(Long candidateSkillId) throws CandidateSkillNotFoundException;

//    List<CandidateSkillDto> findCandidateSkillByCandidateId(Long candidateId) throws CandidateSkillNotFoundException;
}
