package com.elca.jobfairmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.elca.jobfairmanagementsystem.mapper.CandidateMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateRepository;
import com.elca.jobfairmanagementsystem.service.CandidateService;

/**
 *
 * @author ghr
 */
@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidateServiceImpl(CandidateMapper candidateMapper, CandidateRepository candidateRepository) {
        this.candidateMapper = candidateMapper;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<CandidateDto> searchAllCandidate() {
        List<Candidate> listOfCandidate = candidateRepository.findAll();

        return listOfCandidate.stream()
                .map(candidate -> candidateMapper.candidateEntityToCandidateDto(candidate))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCandidate(CandidateDto candidateDto) {
        Candidate newCandidate = candidateMapper.candidateDtoToCandidateEntity(candidateDto);
        candidateRepository.save(newCandidate);

    }

    @Override
    public void deleteCandidate(Long candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    @Override
    public CandidateDto searchCandidateById(Long candidateId) {
        Optional<Candidate> findOneCandidate = candidateRepository.findById(candidateId);
        var candidate = findOneCandidate.orElse(null);
        return candidateMapper.candidateEntityToCandidateDto(candidate);
    }

    @Override
    public void updateCandidate(CandidateDto candidateDto) {
        CandidateDto candidate = searchCandidateById(candidateDto.getCandidateId());
        if (candidate != null) {
            candidate.setAddress(candidateDto.getAddress());
            candidate.setFirstName(candidateDto.getFirstName());
            candidate.setLastName(candidateDto.getLastName());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setMobileNo(candidateDto.getMobileNo());
            candidate.setNationality(candidateDto.getNationality());
            candidate.setPhoneNo(candidateDto.getPhoneNo());
            saveCandidate(candidate);
        } else {
        }
    }
}
