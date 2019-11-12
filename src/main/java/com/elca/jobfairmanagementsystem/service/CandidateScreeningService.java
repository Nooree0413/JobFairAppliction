package com.elca.jobfairmanagementsystem.service;

import java.util.List;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;

/**
 *
 * @author ghr
 */
public interface CandidateScreeningService {
    List<CandidateScreeningDto> findAllCandidateScreening ();
}
