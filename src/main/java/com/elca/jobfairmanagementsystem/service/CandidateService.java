package com.elca.jobfairmanagementsystem.service;

import java.util.List;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;

/**
 * @author ghr
 */
public interface CandidateService {

    List<CandidateDto> findAllCandidate() throws CandidateNotFoundException;

    void saveCandidate(CandidateDto candidateDto);

    void deleteCandidate(Long candidateId) throws CandidateNotFoundException;

    void updateCandidate(CandidateDto candidateDto) throws CandidateNotFoundException;

    CandidateDto findCandidateById(Long candidateId) throws CandidateNotFoundException;

    CandidateDto findCandidateIdByEmail(String email) throws CandidateNotFoundException;
}
