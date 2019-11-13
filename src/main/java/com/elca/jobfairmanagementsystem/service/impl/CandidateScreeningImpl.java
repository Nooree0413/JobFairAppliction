package com.elca.jobfairmanagementsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.service.CandidateScreeningService;

/**
 *
 * @author ghr
 */
@Service
@Transactional
public class CandidateScreeningImpl implements CandidateScreeningService {
    @Override
    public List<CandidateScreeningDto> findAllCandidateScreening() {
        return null;
    }
}
