package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateVenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateVenueJobRepository;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CandidateVenueJobImpl implements CandidateVenueJobService {
    private final CandidateVenueJobMapper candidateVenueJobMapper;
    private final CandidateVenueJobRepository candidateVenueJobRepository;
    private final VenueJobService venueJobService;

    CandidateVenueJobImpl(CandidateVenueJobMapper candidateVenueJobMapper, CandidateVenueJobRepository candidateVenueJobRepository,VenueJobService venueJobService) {
        this.candidateVenueJobMapper = candidateVenueJobMapper;
        this.candidateVenueJobRepository = candidateVenueJobRepository;
        this.venueJobService = venueJobService;
    }

    @Override
    public List<CandidateVenueJobDto> findAllCandidateVenueJobs() throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAll();
        if (candidateVenueJobs.size() != 0) {
            return candidateVenueJobs.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
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
    public List<CandidateVenueJobDto> findAllCandidateByVenueId(Long venueId, Pageable pageable) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findCandidatesByVenueId(venueId,pageable);
            return candidateVenueJobs.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
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
        }else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_AVAILABLE.toString());
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
}
