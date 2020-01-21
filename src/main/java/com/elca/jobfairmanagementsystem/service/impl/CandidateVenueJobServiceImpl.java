package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.dto.PaginationDto;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.Error;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateVenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateVenueJobRepository;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CandidateVenueJobServiceImpl implements CandidateVenueJobService {
    private final CandidateVenueJobMapper candidateVenueJobMapper;
    private final CandidateVenueJobRepository candidateVenueJobRepository;

    public CandidateVenueJobServiceImpl(CandidateVenueJobMapper candidateVenueJobMapper, CandidateVenueJobRepository candidateVenueJobRepository) {
        this.candidateVenueJobMapper = candidateVenueJobMapper;
        this.candidateVenueJobRepository = candidateVenueJobRepository;
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobs(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAll(pageable);
        return getCandidateVenueJobPaginationDto(candidateVenueJobs);
    }

    @Override
    public void deleteCandidateVenueJob(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException {
        var candidateVenueJob = findCandidateVenueJobById(candidateVenueJobId);
        if (candidateVenueJob != null) {
            candidateVenueJobRepository.deleteById(candidateVenueJobId);
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException {
        var getCandidateVenueJobId = findCandidateVenueJobById(candidateVenueJobDto.getCandidateVenueJob());
        if (getCandidateVenueJobId != null) {
            getCandidateVenueJobId.setCandidate(candidateVenueJobDto.getCandidate());
            getCandidateVenueJobId.setVenueJob(candidateVenueJobDto.getVenueJob());
            getCandidateVenueJobId.setJobPriority(candidateVenueJobDto.getJobPriority());
            candidateVenueJobRepository.save(candidateVenueJobMapper.candidateVenueJobDtoToEntity(getCandidateVenueJobId));
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateVenueJobDto findCandidateVenueJobById(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException {
        Optional<CandidateVenueJob> getCandidateVenueJob = candidateVenueJobRepository.findById(candidateVenueJobId);
        var candidateVenueJob = getCandidateVenueJob.orElseThrow(() -> new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString()));
        return candidateVenueJobMapper.candidateVenueJobEntityToDto(candidateVenueJob);
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateByVenueId(Long venueId, Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findCandidatesByVenueId(venueId,pageable);
        return getCandidateVenueJobPaginationDto(candidateVenueJobs);
    }

    @Override
    public CandidateVenueJobCountAllDto countCandidatesByVenue(Long venueId) throws CandidateVenueJobNotFoundException{
        Integer counts = candidateVenueJobRepository.findCountOfCandidatesByVenueId(venueId);
        CandidateVenueJobCountAllDto candidateVenueJobCountAllDto = new CandidateVenueJobCountAllDto();
        candidateVenueJobCountAllDto.setCountCandidates(counts);
        return candidateVenueJobCountAllDto;
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByLastName(String lastName) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobsByLastName = candidateVenueJobRepository.findByLastName(lastName);
        if (candidateVenueJobsByLastName.size() != 0) {
            return candidateVenueJobsByLastName.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByDESC(Long venueId) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobDESC = candidateVenueJobRepository.findCandidatesByVenueIdDESC(venueId);
        if (candidateVenueJobDESC.size() != 0) {
            return candidateVenueJobDESC.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByASC(Long venueId) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobASC = candidateVenueJobRepository.findCandidatesByVenueIdASC(venueId);
        if (candidateVenueJobASC.size() != 0) {
            return candidateVenueJobASC.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobByDESC(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAllCandidatesByDESC(pageable);
        if(candidateVenueJobs == null){
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else{
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobByASC(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAllCandidatesBydASC(pageable);
        if(candidateVenueJobs == null){
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else{
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findCandidateVenueJobByCurrentLevel(String currentLevel, Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findByCurrentLevel(currentLevel,pageable);
        if(candidateVenueJobs == null){
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else{
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    private CandidateVenueJobPaginationDto getCandidateVenueJobPaginationDto(Page<CandidateVenueJob> candidateVenueJobs) throws CandidateVenueJobNotFoundException {
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            List<CandidateVenueJobDto> candidateVenueJobDto = candidateVenueJobs.stream().map(candidateVenueJobMapper::candidateVenueJobEntityToDto).collect(Collectors.toList());
            CandidateVenueJobPaginationDto candidateVenueJobPaginationDto = new CandidateVenueJobPaginationDto();
            candidateVenueJobPaginationDto.setCandidateVenueJobDtoList(candidateVenueJobDto);
            candidateVenueJobPaginationDto.setTotalElements(candidateVenueJobs.getNumberOfElements());
            candidateVenueJobPaginationDto.setTotalPages(candidateVenueJobs.getTotalPages());
            return candidateVenueJobPaginationDto;
        }
    }
}
