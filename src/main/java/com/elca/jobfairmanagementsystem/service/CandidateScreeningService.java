package com.elca.jobfairmanagementsystem.service;

import java.util.List;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.exception.CandidateScreeningNotFoundException;

/**
 *
 * @author ghr
 */
public interface CandidateScreeningService {
    List<CandidateScreeningDto> findAllCandidateScreening() throws CandidateScreeningNotFoundException;
    void saveCandidateScreening(CandidateScreeningDto candidateScreeningDto);
    void deleteCandidateScreening(Long candidateScreeningId) throws CandidateScreeningNotFoundException;
    void updateCandidateScreening(CandidateScreeningDto candidateScreeningDto) throws CandidateScreeningNotFoundException;
    CandidateScreeningDto findByCandidateScreeningId(Long candidateScreeningId) throws CandidateScreeningNotFoundException;
}
