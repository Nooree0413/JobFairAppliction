package com.elca.jobfairmanagementsystem.service;

import java.util.List;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;

/**
 *
 * @author ghr
 */
public interface CandidateService {

    List<CandidateDto> searchAllCandidate();
    void saveCandidate(CandidateDto candidateDto);
    void deleteCandidate(Long candidateId);
    void updateCandidate(Long candidateId);
    CandidateDto searchCandidateById (Long candidateId);
}
