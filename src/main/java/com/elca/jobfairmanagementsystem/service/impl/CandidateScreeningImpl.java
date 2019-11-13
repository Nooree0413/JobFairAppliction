package com.elca.jobfairmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import com.elca.jobfairmanagementsystem.mapper.CandidateScreeningMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateScreeningRepository;
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

    private final CandidateScreeningMapper candidateScreeningMapper;
    private final CandidateScreeningRepository candidateScreeningRepository;

    public CandidateScreeningImpl(CandidateScreeningRepository candidateScreeningRepository,CandidateScreeningMapper candidateScreeningMapper){
        this.candidateScreeningMapper = candidateScreeningMapper;
        this.candidateScreeningRepository = candidateScreeningRepository;
    }

    @Override
    public List<CandidateScreeningDto> findAllCandidateScreening() {
        List<CandidateScreening> listOfCandidateScreening = candidateScreeningRepository.findAll();
        return listOfCandidateScreening.stream()
                .map(candidateScreening -> candidateScreeningMapper.candidateScreeningEntityToDto(candidateScreening))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCandidateScreening(CandidateScreeningDto candidateScreeningDto) {
        var candidateScreening = candidateScreeningMapper.candidateScreeningDtoToEntity(candidateScreeningDto);
        candidateScreeningRepository.save(candidateScreening);
    }

    @Override
    public void deleteCandidateScreening(Long candidateScreeningId) {
        candidateScreeningRepository.deleteById(candidateScreeningId);
    }


    @Override
    public void updateCandidateScreening(CandidateScreeningDto candidateScreeningDto) {
        var candidate = findByCandidateScreeningId(candidateScreeningDto.getCandidateScreeningId());
        candidate.setVenueJobId(candidateScreeningDto.getVenueJobId());
        candidate.setCandidateId(candidateScreeningDto.getCandidateId());
        candidate.setInterviewDate(candidateScreeningDto.getInterviewDate());
        candidate.setInterviewerName(candidateScreeningDto.getInterviewerName());
        candidate.setInterviewerFeedback(candidateScreeningDto.getInterviewerFeedback());
        candidate.setScreeningStatus(candidateScreeningDto.getScreeningStatus());
        candidateScreeningRepository.save(candidateScreeningMapper.candidateScreeningDtoToEntity(candidate));
    }

    @Override
    public CandidateScreeningDto findByCandidateScreeningId(Long candidateScreeningId) {
        Optional<CandidateScreening> findCandidateScreening = candidateScreeningRepository.findById(candidateScreeningId);
        var candidate = findCandidateScreening.orElse(null);
        return candidateScreeningMapper.candidateScreeningEntityToDto(candidate);
    }
}
