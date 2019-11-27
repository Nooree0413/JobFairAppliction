package com.elca.jobfairmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.elca.jobfairmanagementsystem.mapper.CandidateMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateRepository;
import com.elca.jobfairmanagementsystem.service.CandidateService;

/**
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
    public List<CandidateDto> findAllCandidate() throws CandidateNotFoundException {
        List<Candidate> listOfCandidate = candidateRepository.findAll();
        if (listOfCandidate.size() != 0) {
            return listOfCandidate.stream()
                    .map(candidateMapper::candidateEntityToCandidateDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateNotFoundException(ErrorMessages.NO_CANDIDATE_AVAILABLE.toString());
        }
    }

    @Override
    public void saveCandidate(CandidateDto candidateDto) {
        Candidate newCandidate = candidateMapper.candidateDtoToCandidateEntity(candidateDto);
        candidateRepository.save(newCandidate);
    }

    @Override
    public void deleteCandidate(Long candidateId) throws CandidateNotFoundException {
        var candidate = findCandidateById(candidateId);
        if (candidate != null) {
            candidateRepository.deleteById(candidateId);
        } else {
            throw new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateDto findCandidateById(Long candidateId) throws CandidateNotFoundException {
        Optional<Candidate> findOneCandidate = candidateRepository.findById(candidateId);
        var candidate = findOneCandidate.orElseThrow(() -> new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString()));
        return candidateMapper.candidateEntityToCandidateDto(candidate);
    }

    @Override
    public void updateCandidate(CandidateDto candidateDto) throws CandidateNotFoundException {
        CandidateDto candidate = findCandidateById(candidateDto.getCandidateId());
        if (candidate != null) {
            candidate.setAddress(candidateDto.getAddress());
            candidate.setFirstName(candidateDto.getFirstName());
            candidate.setLastName(candidateDto.getLastName());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setNationality(candidateDto.getNationality());
            saveCandidate(candidate);
        } else {
            throw new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString());
        }
    }
}
