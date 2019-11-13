package com.elca.jobfairmanagementsystem.service;

import java.util.List;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;

/**
 *
 * @author ghr
 */
public interface CandidateScreeningService {
    List<CandidateScreeningDto> findAllCandidateScreening();
    void saveCandidateScreening(CandidateScreeningDto candidateScreeningDto);
    void deleteCandidateScreening(Long candidateScreeningId);
    void updateCandidateScreening(CandidateScreeningDto candidateScreeningDto);
    CandidateScreeningDto findByCandidateScreeningId(Long candidateScreeningId);
}
