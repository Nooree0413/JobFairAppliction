package com.elca.jobfairmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import com.elca.jobfairmanagementsystem.exception.CandidateScreeningNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateScreeningMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateScreeningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.service.CandidateScreeningService;

@Service
@Transactional
public class CandidateScreeningServiceImpl implements CandidateScreeningService {

    private final CandidateScreeningMapper candidateScreeningMapper;
    private final CandidateScreeningRepository candidateScreeningRepository;

    public CandidateScreeningServiceImpl(CandidateScreeningRepository candidateScreeningRepository, CandidateScreeningMapper candidateScreeningMapper) {
        this.candidateScreeningMapper = candidateScreeningMapper;
        this.candidateScreeningRepository = candidateScreeningRepository;
    }

    @Override
    public List<CandidateScreeningDto> findAllCandidateScreening() throws CandidateScreeningNotFoundException {
        List<CandidateScreening> listOfCandidateScreening = candidateScreeningRepository.findAll();
        if (listOfCandidateScreening.size() != 0) {
            return listOfCandidateScreening.stream()
                    .map(candidateScreeningMapper::candidateScreeningEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateScreeningNotFoundException(ErrorMessages.NO_CANDIDATE_SCREENING_AVAILABLE.toString());
        }
    }

    @Override
    public void saveCandidateScreening(CandidateScreeningDto candidateScreeningDto) {
        var candidateScreening = candidateScreeningMapper.candidateScreeningDtoToEntity(candidateScreeningDto);
        candidateScreeningRepository.save(candidateScreening);
    }

    @Override
    public void deleteCandidateScreening(Long candidateScreeningId) throws CandidateScreeningNotFoundException {
        var candidateScreening = findByCandidateScreeningId(candidateScreeningId);
        if (candidateScreening != null) {
            candidateScreeningRepository.deleteById(candidateScreeningId);
        } else {
            throw new CandidateScreeningNotFoundException(ErrorMessages.CANDIDATE_SCREENING_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateCandidateScreening(CandidateScreeningDto candidateScreeningDto) throws CandidateScreeningNotFoundException {
        var candidate = findByCandidateScreeningId(candidateScreeningDto.getCandidateScreeningId());
        if (candidate != null) {
            candidate.setCandidateId(candidateScreeningDto.getCandidateId());
            candidate.setInterviewDate(candidateScreeningDto.getInterviewDate());
            candidate.setInterviewerName(candidateScreeningDto.getInterviewerName());
            candidate.setInterviewerFeedback(candidateScreeningDto.getInterviewerFeedback());
            candidate.setScreeningStatus(candidateScreeningDto.getScreeningStatus());
            candidateScreeningRepository.save(candidateScreeningMapper.candidateScreeningDtoToEntity(candidate));
        } else {
            throw new CandidateScreeningNotFoundException(ErrorMessages.CANDIDATE_SCREENING_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateScreeningDto findByCandidateScreeningId(Long candidateScreeningId) throws CandidateScreeningNotFoundException {
        Optional<CandidateScreening> findCandidateScreening = candidateScreeningRepository.findById(candidateScreeningId);
        var candidate = findCandidateScreening.orElseThrow(() -> new CandidateScreeningNotFoundException(ErrorMessages.CANDIDATE_SCREENING_NOT_FOUND.toString()));
        return candidateScreeningMapper.candidateScreeningEntityToDto(candidate);
    }
}
